package com.sensei.app.web.rest;

import com.sensei.app.Ignition5BaseApp;

import com.sensei.app.domain.Asset;
import com.sensei.app.repository.AssetRepository;
import com.sensei.app.repository.search.AssetSearchRepository;
import com.sensei.app.service.AssetService;
import com.sensei.app.service.dto.AssetDTO;
import com.sensei.app.service.mapper.AssetMapper;
import com.sensei.app.web.rest.errors.ExceptionTranslator;
import com.sensei.app.service.dto.AssetCriteria;
import com.sensei.app.service.AssetQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import static com.sensei.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AssetResource REST controller.
 *
 * @see AssetResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Ignition5BaseApp.class)
public class AssetResourceIntTest {

    private static final String DEFAULT_ASSET_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final LocalDate DEFAULT_PROCUREMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROCUREMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ASSET_ID = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VALIDITY_AVAILABLE = false;
    private static final Boolean UPDATED_VALIDITY_AVAILABLE = true;

    @Autowired
    private AssetRepository assetRepository;


    @Autowired
    private AssetMapper assetMapper;
    

    @Autowired
    private AssetService assetService;

    /**
     * This repository is mocked in the com.sensei.app.repository.search test package.
     *
     * @see com.sensei.app.repository.search.AssetSearchRepositoryMockConfiguration
     */
    @Autowired
    private AssetSearchRepository mockAssetSearchRepository;

    @Autowired
    private AssetQueryService assetQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAssetMockMvc;

    private Asset asset;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AssetResource assetResource = new AssetResource(assetService, assetQueryService);
        this.restAssetMockMvc = MockMvcBuilders.standaloneSetup(assetResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Asset createEntity(EntityManager em) {
        Asset asset = new Asset()
            .assetType(DEFAULT_ASSET_TYPE)
            .price(DEFAULT_PRICE)
            .procurementDate(DEFAULT_PROCUREMENT_DATE)
            .assetId(DEFAULT_ASSET_ID)
            .validityAvailable(DEFAULT_VALIDITY_AVAILABLE);
        return asset;
    }

    @Before
    public void initTest() {
        asset = createEntity(em);
    }

    @Test
    @Transactional
    public void createAsset() throws Exception {
        int databaseSizeBeforeCreate = assetRepository.findAll().size();

        // Create the Asset
        AssetDTO assetDTO = assetMapper.toDto(asset);
        restAssetMockMvc.perform(post("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetDTO)))
            .andExpect(status().isCreated());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeCreate + 1);
        Asset testAsset = assetList.get(assetList.size() - 1);
        assertThat(testAsset.getAssetType()).isEqualTo(DEFAULT_ASSET_TYPE);
        assertThat(testAsset.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testAsset.getProcurementDate()).isEqualTo(DEFAULT_PROCUREMENT_DATE);
        assertThat(testAsset.getAssetId()).isEqualTo(DEFAULT_ASSET_ID);
        assertThat(testAsset.isValidityAvailable()).isEqualTo(DEFAULT_VALIDITY_AVAILABLE);

        // Validate the Asset in Elasticsearch
        verify(mockAssetSearchRepository, times(1)).save(testAsset);
    }

    @Test
    @Transactional
    public void createAssetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assetRepository.findAll().size();

        // Create the Asset with an existing ID
        asset.setId(1L);
        AssetDTO assetDTO = assetMapper.toDto(asset);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssetMockMvc.perform(post("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeCreate);

        // Validate the Asset in Elasticsearch
        verify(mockAssetSearchRepository, times(0)).save(asset);
    }

    @Test
    @Transactional
    public void checkAssetTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = assetRepository.findAll().size();
        // set the field null
        asset.setAssetType(null);

        // Create the Asset, which fails.
        AssetDTO assetDTO = assetMapper.toDto(asset);

        restAssetMockMvc.perform(post("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetDTO)))
            .andExpect(status().isBadRequest());

        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAssetIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = assetRepository.findAll().size();
        // set the field null
        asset.setAssetId(null);

        // Create the Asset, which fails.
        AssetDTO assetDTO = assetMapper.toDto(asset);

        restAssetMockMvc.perform(post("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetDTO)))
            .andExpect(status().isBadRequest());

        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAssets() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList
        restAssetMockMvc.perform(get("/api/assets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(asset.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetType").value(hasItem(DEFAULT_ASSET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].procurementDate").value(hasItem(DEFAULT_PROCUREMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].assetId").value(hasItem(DEFAULT_ASSET_ID.toString())))
            .andExpect(jsonPath("$.[*].validityAvailable").value(hasItem(DEFAULT_VALIDITY_AVAILABLE.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getAsset() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get the asset
        restAssetMockMvc.perform(get("/api/assets/{id}", asset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(asset.getId().intValue()))
            .andExpect(jsonPath("$.assetType").value(DEFAULT_ASSET_TYPE.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.procurementDate").value(DEFAULT_PROCUREMENT_DATE.toString()))
            .andExpect(jsonPath("$.assetId").value(DEFAULT_ASSET_ID.toString()))
            .andExpect(jsonPath("$.validityAvailable").value(DEFAULT_VALIDITY_AVAILABLE.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllAssetsByAssetTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where assetType equals to DEFAULT_ASSET_TYPE
        defaultAssetShouldBeFound("assetType.equals=" + DEFAULT_ASSET_TYPE);

        // Get all the assetList where assetType equals to UPDATED_ASSET_TYPE
        defaultAssetShouldNotBeFound("assetType.equals=" + UPDATED_ASSET_TYPE);
    }

    @Test
    @Transactional
    public void getAllAssetsByAssetTypeIsInShouldWork() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where assetType in DEFAULT_ASSET_TYPE or UPDATED_ASSET_TYPE
        defaultAssetShouldBeFound("assetType.in=" + DEFAULT_ASSET_TYPE + "," + UPDATED_ASSET_TYPE);

        // Get all the assetList where assetType equals to UPDATED_ASSET_TYPE
        defaultAssetShouldNotBeFound("assetType.in=" + UPDATED_ASSET_TYPE);
    }

    @Test
    @Transactional
    public void getAllAssetsByAssetTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where assetType is not null
        defaultAssetShouldBeFound("assetType.specified=true");

        // Get all the assetList where assetType is null
        defaultAssetShouldNotBeFound("assetType.specified=false");
    }

    @Test
    @Transactional
    public void getAllAssetsByPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where price equals to DEFAULT_PRICE
        defaultAssetShouldBeFound("price.equals=" + DEFAULT_PRICE);

        // Get all the assetList where price equals to UPDATED_PRICE
        defaultAssetShouldNotBeFound("price.equals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllAssetsByPriceIsInShouldWork() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where price in DEFAULT_PRICE or UPDATED_PRICE
        defaultAssetShouldBeFound("price.in=" + DEFAULT_PRICE + "," + UPDATED_PRICE);

        // Get all the assetList where price equals to UPDATED_PRICE
        defaultAssetShouldNotBeFound("price.in=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllAssetsByPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where price is not null
        defaultAssetShouldBeFound("price.specified=true");

        // Get all the assetList where price is null
        defaultAssetShouldNotBeFound("price.specified=false");
    }

    @Test
    @Transactional
    public void getAllAssetsByProcurementDateIsEqualToSomething() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where procurementDate equals to DEFAULT_PROCUREMENT_DATE
        defaultAssetShouldBeFound("procurementDate.equals=" + DEFAULT_PROCUREMENT_DATE);

        // Get all the assetList where procurementDate equals to UPDATED_PROCUREMENT_DATE
        defaultAssetShouldNotBeFound("procurementDate.equals=" + UPDATED_PROCUREMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllAssetsByProcurementDateIsInShouldWork() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where procurementDate in DEFAULT_PROCUREMENT_DATE or UPDATED_PROCUREMENT_DATE
        defaultAssetShouldBeFound("procurementDate.in=" + DEFAULT_PROCUREMENT_DATE + "," + UPDATED_PROCUREMENT_DATE);

        // Get all the assetList where procurementDate equals to UPDATED_PROCUREMENT_DATE
        defaultAssetShouldNotBeFound("procurementDate.in=" + UPDATED_PROCUREMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllAssetsByProcurementDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where procurementDate is not null
        defaultAssetShouldBeFound("procurementDate.specified=true");

        // Get all the assetList where procurementDate is null
        defaultAssetShouldNotBeFound("procurementDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllAssetsByProcurementDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where procurementDate greater than or equals to DEFAULT_PROCUREMENT_DATE
        defaultAssetShouldBeFound("procurementDate.greaterOrEqualThan=" + DEFAULT_PROCUREMENT_DATE);

        // Get all the assetList where procurementDate greater than or equals to UPDATED_PROCUREMENT_DATE
        defaultAssetShouldNotBeFound("procurementDate.greaterOrEqualThan=" + UPDATED_PROCUREMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllAssetsByProcurementDateIsLessThanSomething() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where procurementDate less than or equals to DEFAULT_PROCUREMENT_DATE
        defaultAssetShouldNotBeFound("procurementDate.lessThan=" + DEFAULT_PROCUREMENT_DATE);

        // Get all the assetList where procurementDate less than or equals to UPDATED_PROCUREMENT_DATE
        defaultAssetShouldBeFound("procurementDate.lessThan=" + UPDATED_PROCUREMENT_DATE);
    }


    @Test
    @Transactional
    public void getAllAssetsByAssetIdIsEqualToSomething() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where assetId equals to DEFAULT_ASSET_ID
        defaultAssetShouldBeFound("assetId.equals=" + DEFAULT_ASSET_ID);

        // Get all the assetList where assetId equals to UPDATED_ASSET_ID
        defaultAssetShouldNotBeFound("assetId.equals=" + UPDATED_ASSET_ID);
    }

    @Test
    @Transactional
    public void getAllAssetsByAssetIdIsInShouldWork() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where assetId in DEFAULT_ASSET_ID or UPDATED_ASSET_ID
        defaultAssetShouldBeFound("assetId.in=" + DEFAULT_ASSET_ID + "," + UPDATED_ASSET_ID);

        // Get all the assetList where assetId equals to UPDATED_ASSET_ID
        defaultAssetShouldNotBeFound("assetId.in=" + UPDATED_ASSET_ID);
    }

    @Test
    @Transactional
    public void getAllAssetsByAssetIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where assetId is not null
        defaultAssetShouldBeFound("assetId.specified=true");

        // Get all the assetList where assetId is null
        defaultAssetShouldNotBeFound("assetId.specified=false");
    }

    @Test
    @Transactional
    public void getAllAssetsByValidityAvailableIsEqualToSomething() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where validityAvailable equals to DEFAULT_VALIDITY_AVAILABLE
        defaultAssetShouldBeFound("validityAvailable.equals=" + DEFAULT_VALIDITY_AVAILABLE);

        // Get all the assetList where validityAvailable equals to UPDATED_VALIDITY_AVAILABLE
        defaultAssetShouldNotBeFound("validityAvailable.equals=" + UPDATED_VALIDITY_AVAILABLE);
    }

    @Test
    @Transactional
    public void getAllAssetsByValidityAvailableIsInShouldWork() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where validityAvailable in DEFAULT_VALIDITY_AVAILABLE or UPDATED_VALIDITY_AVAILABLE
        defaultAssetShouldBeFound("validityAvailable.in=" + DEFAULT_VALIDITY_AVAILABLE + "," + UPDATED_VALIDITY_AVAILABLE);

        // Get all the assetList where validityAvailable equals to UPDATED_VALIDITY_AVAILABLE
        defaultAssetShouldNotBeFound("validityAvailable.in=" + UPDATED_VALIDITY_AVAILABLE);
    }

    @Test
    @Transactional
    public void getAllAssetsByValidityAvailableIsNullOrNotNull() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        // Get all the assetList where validityAvailable is not null
        defaultAssetShouldBeFound("validityAvailable.specified=true");

        // Get all the assetList where validityAvailable is null
        defaultAssetShouldNotBeFound("validityAvailable.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultAssetShouldBeFound(String filter) throws Exception {
        restAssetMockMvc.perform(get("/api/assets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(asset.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetType").value(hasItem(DEFAULT_ASSET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].procurementDate").value(hasItem(DEFAULT_PROCUREMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].assetId").value(hasItem(DEFAULT_ASSET_ID.toString())))
            .andExpect(jsonPath("$.[*].validityAvailable").value(hasItem(DEFAULT_VALIDITY_AVAILABLE.booleanValue())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultAssetShouldNotBeFound(String filter) throws Exception {
        restAssetMockMvc.perform(get("/api/assets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    public void getNonExistingAsset() throws Exception {
        // Get the asset
        restAssetMockMvc.perform(get("/api/assets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAsset() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        int databaseSizeBeforeUpdate = assetRepository.findAll().size();

        // Update the asset
        Asset updatedAsset = assetRepository.findById(asset.getId()).get();
        // Disconnect from session so that the updates on updatedAsset are not directly saved in db
        em.detach(updatedAsset);
        updatedAsset
            .assetType(UPDATED_ASSET_TYPE)
            .price(UPDATED_PRICE)
            .procurementDate(UPDATED_PROCUREMENT_DATE)
            .assetId(UPDATED_ASSET_ID)
            .validityAvailable(UPDATED_VALIDITY_AVAILABLE);
        AssetDTO assetDTO = assetMapper.toDto(updatedAsset);

        restAssetMockMvc.perform(put("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetDTO)))
            .andExpect(status().isOk());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);
        Asset testAsset = assetList.get(assetList.size() - 1);
        assertThat(testAsset.getAssetType()).isEqualTo(UPDATED_ASSET_TYPE);
        assertThat(testAsset.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testAsset.getProcurementDate()).isEqualTo(UPDATED_PROCUREMENT_DATE);
        assertThat(testAsset.getAssetId()).isEqualTo(UPDATED_ASSET_ID);
        assertThat(testAsset.isValidityAvailable()).isEqualTo(UPDATED_VALIDITY_AVAILABLE);

        // Validate the Asset in Elasticsearch
        verify(mockAssetSearchRepository, times(1)).save(testAsset);
    }

    @Test
    @Transactional
    public void updateNonExistingAsset() throws Exception {
        int databaseSizeBeforeUpdate = assetRepository.findAll().size();

        // Create the Asset
        AssetDTO assetDTO = assetMapper.toDto(asset);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAssetMockMvc.perform(put("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Asset in the database
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Asset in Elasticsearch
        verify(mockAssetSearchRepository, times(0)).save(asset);
    }

    @Test
    @Transactional
    public void deleteAsset() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);

        int databaseSizeBeforeDelete = assetRepository.findAll().size();

        // Get the asset
        restAssetMockMvc.perform(delete("/api/assets/{id}", asset.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Asset> assetList = assetRepository.findAll();
        assertThat(assetList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Asset in Elasticsearch
        verify(mockAssetSearchRepository, times(1)).deleteById(asset.getId());
    }

    @Test
    @Transactional
    public void searchAsset() throws Exception {
        // Initialize the database
        assetRepository.saveAndFlush(asset);
        when(mockAssetSearchRepository.search(queryStringQuery("id:" + asset.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(asset), PageRequest.of(0, 1), 1));
        // Search the asset
        restAssetMockMvc.perform(get("/api/_search/assets?query=id:" + asset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(asset.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetType").value(hasItem(DEFAULT_ASSET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].procurementDate").value(hasItem(DEFAULT_PROCUREMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].assetId").value(hasItem(DEFAULT_ASSET_ID.toString())))
            .andExpect(jsonPath("$.[*].validityAvailable").value(hasItem(DEFAULT_VALIDITY_AVAILABLE.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Asset.class);
        Asset asset1 = new Asset();
        asset1.setId(1L);
        Asset asset2 = new Asset();
        asset2.setId(asset1.getId());
        assertThat(asset1).isEqualTo(asset2);
        asset2.setId(2L);
        assertThat(asset1).isNotEqualTo(asset2);
        asset1.setId(null);
        assertThat(asset1).isNotEqualTo(asset2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssetDTO.class);
        AssetDTO assetDTO1 = new AssetDTO();
        assetDTO1.setId(1L);
        AssetDTO assetDTO2 = new AssetDTO();
        assertThat(assetDTO1).isNotEqualTo(assetDTO2);
        assetDTO2.setId(assetDTO1.getId());
        assertThat(assetDTO1).isEqualTo(assetDTO2);
        assetDTO2.setId(2L);
        assertThat(assetDTO1).isNotEqualTo(assetDTO2);
        assetDTO1.setId(null);
        assertThat(assetDTO1).isNotEqualTo(assetDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(assetMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(assetMapper.fromId(null)).isNull();
    }
}

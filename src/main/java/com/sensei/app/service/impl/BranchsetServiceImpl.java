package com.sensei.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sensei.app.domain.Branchset;
import com.sensei.app.repository.BranchRepository;
import com.sensei.app.repository.BranchsetRepository;
import com.sensei.app.service.BranchsetBranchService;
import com.sensei.app.service.BranchsetService;
import com.sensei.app.service.dto.BranchDTO;
import com.sensei.app.service.dto.BranchsetDTO;
import com.sensei.app.service.mapper.BranchsetMapper;

/**
 * Service Implementation for managing Branchset.
 */
@Service
@Transactional
public class BranchsetServiceImpl implements BranchsetService {

    private final Logger log = LoggerFactory.getLogger(BranchsetServiceImpl.class);

    private final BranchsetRepository branchsetRepository;

    private final BranchsetMapper branchsetMapper;

    private final BranchsetBranchService branchsetBranchService;
    
    private final BranchRepository branchRepository;
    
	public BranchsetServiceImpl(BranchsetRepository branchsetRepository, BranchsetMapper branchsetMapper,
			BranchsetBranchService branchsetBranchService,
			BranchRepository branchRepository) {
		this.branchsetRepository = branchsetRepository;
		this.branchsetMapper = branchsetMapper;
		this.branchsetBranchService = branchsetBranchService;
		this.branchRepository = branchRepository;
	}

    /**
     * Save a branchset.
     *
     * @param branchsetDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BranchsetDTO save(BranchsetDTO branchsetDTO) {
        log.debug("Request to save Branchset : {}", branchsetDTO);
        Branchset branchset = branchsetMapper.toEntity(branchsetDTO);
        branchset = branchsetRepository.save(branchset);
        BranchsetDTO result = branchsetMapper.toDto(branchset);
        for(Long branchId : branchsetDTO.getBranchIds()) {
        	branchsetBranchService.createBranchAndBranchsetMapping(branchId, result.getId());
        }
        return result;
    }

    /**
     * Get all the branchsets.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BranchsetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Branchsets");
        Page<BranchsetDTO> branchSetDtoPage = branchsetRepository.findAll(pageable)
        .map(branchsetMapper::toDto);
        branchSetDtoPage.forEach(branchSetDto -> {
        	branchSetDto.setBranches(branchRepository.findBranchesForBranchsetId(branchSetDto.getId()));
        });
        return branchSetDtoPage;
    }


    /**
     * Get one branchset by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BranchsetDTO> findOne(Long id) {
        log.debug("Request to get Branchset : {}", id);
        Optional<BranchsetDTO> branchsetDTO =  branchsetRepository.findById(id)
            .map(branchsetMapper::toDto);
        if(branchsetDTO.isPresent()) {
        	branchsetDTO.get().setBranches(branchRepository.findBranchesForBranchsetId(id));
        }
		return branchsetDTO;
    }

    /**
     * Delete the branchset by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Branchset : {}", id);
        branchsetRepository.deleteById(id);
    }

	@Override
	public BranchsetDTO createBranchSetForBranch(BranchDTO branch) {
		Branchset branchset = branchsetRepository.findByNameAndCode(branch.getName(),branch.getCode());
		if(branchset == null) {
			branchset = new Branchset();
		}
		branchset.setName(branch.getName());
		branchset.setCode(branch.getCode());
		branchset = branchsetRepository.save(branchset);
		return branchsetMapper.toDto(branchset);
	}

	@Override
	public List<BranchsetDTO> findAll() {
		return branchsetMapper.toDto(branchsetRepository.findAll());
	}
	
	@Override
	@Transactional
	public BranchsetDTO update(BranchsetDTO branchsetDTO) {
		Branchset branchset = branchsetMapper.toEntity(branchsetDTO);
		branchset = branchsetRepository.save(branchset);
		BranchsetDTO result = branchsetMapper.toDto(branchset);
		branchsetBranchService.deleteAllMappingFor(branchsetDTO.getId());
        for(Long branchId : branchsetDTO.getBranchIds()) {
        	branchsetBranchService.createBranchAndBranchsetMapping(branchId, result.getId());
        }
		return result;
	}
	
}

package com.sensei.app.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of AssetSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class AssetSearchRepositoryMockConfiguration {

    @MockBean
    private AssetSearchRepository mockAssetSearchRepository;

}

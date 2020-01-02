package com.sensei.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sensei.app.domain.BranchsetBranch;
import com.sensei.app.repository.BranchsetBranchRepository;
import com.sensei.app.service.BranchsetBranchService;
import com.sensei.app.service.dto.BranchsetBranchDTO;
import com.sensei.app.service.mapper.BranchsetBranchMapper;

/**
 * Service Implementation for managing BranchsetBranch.
 */
@Service
@Transactional
public class BranchsetBranchServiceImpl implements BranchsetBranchService {

    private final Logger log = LoggerFactory.getLogger(BranchsetBranchServiceImpl.class);

    private final BranchsetBranchRepository branchsetBranchRepository;

    private final BranchsetBranchMapper branchsetBranchMapper;

	public BranchsetBranchServiceImpl(BranchsetBranchRepository branchsetBranchRepository,
			BranchsetBranchMapper branchsetBranchMapper) {
		this.branchsetBranchRepository = branchsetBranchRepository;
		this.branchsetBranchMapper = branchsetBranchMapper;
	}

    /**
     * Save a branchsetBranch.
     *
     * @param branchsetBranchDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BranchsetBranchDTO save(BranchsetBranchDTO branchsetBranchDTO) {
        log.debug("Request to save BranchsetBranch : {}", branchsetBranchDTO);
        BranchsetBranch branchsetBranch = branchsetBranchMapper.toEntity(branchsetBranchDTO);
        branchsetBranch = branchsetBranchRepository.save(branchsetBranch);
        BranchsetBranchDTO result = branchsetBranchMapper.toDto(branchsetBranch);
        return result;
    }

    /**
     * Get all the branchsetBranches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BranchsetBranchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BranchsetBranches");
        return branchsetBranchRepository.findAll(pageable)
            .map(branchsetBranchMapper::toDto);
    }


    /**
     * Get one branchsetBranch by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BranchsetBranchDTO> findOne(Long id) {
        log.debug("Request to get BranchsetBranch : {}", id);
        return branchsetBranchRepository.findById(id)
            .map(branchsetBranchMapper::toDto);
    }

    /**
     * Delete the branchsetBranch by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BranchsetBranch : {}", id);
        branchsetBranchRepository.deleteById(id);
    }

	@Override
	public void createBranchAndBranchsetMapping(Long branchId, Long branchsetId) {
		BranchsetBranch branchsetBranch = branchsetBranchRepository.findByBranchIdAndBranchsetId(branchId, branchsetId);
		if(branchsetBranch == null) {
			branchsetBranch = new BranchsetBranch();
			branchsetBranch.setBranchId(branchId);
			branchsetBranch.setBranchsetId(branchsetId);
			branchsetBranchRepository.save(branchsetBranch);
		}
	}

	@Override
	public List<BranchsetBranch> findByBranchsetId(Long branchsetId) {
		return branchsetBranchRepository.findAllByBranchsetId(branchsetId);
	}

	@Override
	public void deleteAllMappingFor(Long branchSetId) {
		branchsetBranchRepository.deleteBranchSetFor(branchSetId);
		
	}
	
	
}

package com.sensei.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sensei.app.domain.Branch;
import com.sensei.app.domain.BranchsetBranch;
import com.sensei.app.repository.BranchRepository;
import com.sensei.app.service.BranchService;
import com.sensei.app.service.BranchsetBranchService;
import com.sensei.app.service.BranchsetService;
import com.sensei.app.service.dto.BranchDTO;
import com.sensei.app.service.dto.BranchsetDTO;
import com.sensei.app.service.mapper.BranchMapper;

/**
 * Service Implementation for managing Branch.
 */
@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    private final Logger log = LoggerFactory.getLogger(BranchServiceImpl.class);

    private final BranchRepository branchRepository;

    private final BranchMapper branchMapper;
    
    private final BranchsetService branchsetService;
    
    private final BranchsetBranchService branchsetBranchService;

    public BranchServiceImpl(BranchRepository branchRepository, BranchMapper branchMapper, 
    	BranchsetService branchsetService,
    	BranchsetBranchService branchsetBranchService) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
        this.branchsetService  = branchsetService;
        this.branchsetBranchService = branchsetBranchService;
    }

    /**
     * Save a branch.
     *
     * @param branchDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BranchDTO save(BranchDTO branchDTO) {
        log.debug("Request to save Branch : {}", branchDTO);
        Branch branch = branchMapper.toEntity(branchDTO);
        branch = branchRepository.save(branch);
        BranchDTO result = branchMapper.toDto(branch);
        BranchsetDTO branchset = branchsetService.createBranchSetForBranch(result);
        branchsetBranchService.createBranchAndBranchsetMapping(result.getId(),branchset.getId());
        return result;
    }

    /**
     * Get all the branches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BranchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Branches");
        return branchRepository.findAll(pageable)
            .map(branchMapper::toDto);
    }


    /**
     * Get one branch by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BranchDTO> findOne(Long id) {
        log.debug("Request to get Branch : {}", id);
        return branchRepository.findById(id)
            .map(branchMapper::toDto);
    }

    /**
     * Delete the branch by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Branch : {}", id);
        branchRepository.deleteById(id);
    }

	@Override
	public List<BranchDTO> findAll() {
		List<Branch> branches = branchRepository.findAll();
		return branchMapper.toDto(branches);
	}

	@Override
	public BranchDTO update(BranchDTO branchDTO) {
		Branch branch = branchMapper.toEntity(branchDTO);
		branch = branchRepository.save(branch);
        BranchDTO result = branchMapper.toDto(branch);
		return result;
	}

	@Override
	public List<BranchDTO> getAllBranchesByBranchsetId(Long barnchsetId) {
		List<BranchsetBranch> branchsetBranches = branchsetBranchService.findByBranchsetId(barnchsetId);
        List<BranchDTO> branches = new ArrayList<>();
        for(BranchsetBranch branchsetBranch : branchsetBranches) {
     	   Optional<BranchDTO> branch = findOne(branchsetBranch.getBranchId());
     	   branches.add(branch.get());
        }
        return branches;
	}

	@Override
	public BranchDTO findByBranchCode(String branchCode) {
		Branch branch = branchRepository.findByCode(branchCode);
		return branchMapper.toDto(branch);
	}
}

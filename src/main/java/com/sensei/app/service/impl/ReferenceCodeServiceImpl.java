package com.sensei.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sensei.app.domain.ReferenceCode;
import com.sensei.app.repository.ReferenceCodeRepository;
import com.sensei.app.service.ReferenceCodeService;
import com.sensei.app.service.dto.ReferenceCodeDTO;
import com.sensei.app.service.mapper.ReferenceCodeMapper;

/**
 * Service Implementation for managing ReferenceCode.
 */
@Service
@Transactional
public class ReferenceCodeServiceImpl implements ReferenceCodeService {

	private final Logger log = LoggerFactory.getLogger(ReferenceCodeServiceImpl.class);

	private final ReferenceCodeRepository referenceCodeRepository;

	private final ReferenceCodeMapper referenceCodeMapper;

	public ReferenceCodeServiceImpl(ReferenceCodeRepository referenceCodeRepository,
			ReferenceCodeMapper referenceCodeMapper) {
		this.referenceCodeRepository = referenceCodeRepository;
		this.referenceCodeMapper = referenceCodeMapper;
	}

	/**
	 * Save a referenceCode.
	 *
	 * @param referenceCodeDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public ReferenceCodeDTO save(ReferenceCodeDTO referenceCodeDTO) {
		log.debug("Request to save ReferenceCode : {}", referenceCodeDTO);
		ReferenceCode referenceCode = referenceCodeMapper.toEntity(referenceCodeDTO);
		referenceCode = referenceCodeRepository.save(referenceCode);
		ReferenceCodeDTO result = referenceCodeMapper.toDto(referenceCode);
		return result;
	}

	/**
	 * Get all the referenceCodes.
	 * 
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<ReferenceCodeDTO> findAll(Pageable pageable) {
		log.debug("Request to get all ReferenceCodes");
		Page<ReferenceCode> result = referenceCodeRepository.findAll(pageable);
		return result.map(referenceCode -> referenceCodeMapper.toDto(referenceCode));
	}

	/**
	 * Get one referenceCode by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public ReferenceCodeDTO findOne(Long id) {
		log.debug("Request to get ReferenceCode : {}", id);
		Optional<ReferenceCode> referenceCode = referenceCodeRepository.findById(id);
		ReferenceCodeDTO referenceCodeDTO = referenceCodeMapper.toDto(referenceCode.get());
		return referenceCodeDTO;
	}

	/**
	 * Delete the referenceCode by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete ReferenceCode : {}", id);
		referenceCodeRepository.deleteById(id);
	}

	@Override
	public List<String> findDistinctClassifiers() {
		return referenceCodeRepository.findDistinctClassifiers();
	}

	@Override
	public List<ReferenceCodeDTO> find(String classifier, Integer status) {
		List<ReferenceCode> list = null;
		if (classifier == null || StringUtils.isEmpty(classifier) && status == null) {
			list = referenceCodeRepository.findAll();
		} else if (classifier == null || StringUtils.isEmpty(classifier) && status != null) {
			list = referenceCodeRepository.findByStatus(status);
		} else if (classifier != null && status == null) {
			list = referenceCodeRepository.findByClassifier(classifier);
		} else {
			list = referenceCodeRepository.findByClassifierAndStatus(classifier, status);
		}

		return list.stream().map(referenceCodeMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<ReferenceCodeDTO> findChildReferenceCodes(String classifier, String parentReferenceCode,
			Integer status) {
		List<ReferenceCode> childReferenceCodes = referenceCodeRepository
				.findByClassifierAndParentReferenceCodeAndStatus(classifier, parentReferenceCode, status);
		return childReferenceCodes.stream().map(referenceCodeMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ReferenceCodeDTO> findParentReferenceCodes(String classifier, Integer status) {
		List<ReferenceCode> parentReferenceCodes = referenceCodeRepository.findParentReferenceCodes(classifier, status);
		return parentReferenceCodes.stream().map(referenceCodeMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void disable(Long id, Integer status) {
		Optional<ReferenceCode> referenceCode = referenceCodeRepository.findById(id);
		if (referenceCode != null) {
			referenceCode.get().setStatus(status);
			referenceCodeRepository.saveAndFlush(referenceCode.get());
		}
	}
}

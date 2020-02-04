package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.Grade;
import com.sensei.app.domain.Ladder;
import com.sensei.app.repository.LadderRepository;
import com.sensei.app.service.LadderService;
import com.sensei.app.service.dto.LadderDTO;
import com.sensei.app.service.mapper.LadderMapper;

@Service
@Transactional

public class LadderServiceImpl implements LadderService{
	
	private final LadderMapper ladderMapper;
	
	private final LadderRepository ladderRepository;
	
	

	public LadderServiceImpl(LadderMapper ladderMapper, LadderRepository ladderRepository) {
		this.ladderMapper = ladderMapper;
		this.ladderRepository = ladderRepository;
	}

	@Override
	public LadderDTO save(LadderDTO ladderdto) {
		Ladder ladder=ladderMapper.toEntity(ladderdto);
		ladder=ladderRepository.save(ladder);
		LadderDTO result=ladderMapper.toDto(ladder);
		return result;
	}

	@Override
	public Page<LadderDTO> findall(Pageable pagable) {
		Page<Ladder> result=ladderRepository.findAll(pagable);
		return result.map(Ladder ->ladderMapper.toDto(Ladder));
	}

	@Override
	public Optional<LadderDTO> findOne(Long id) {
		Optional<Ladder> result=ladderRepository.findById(id);
		return result.map(Ladder ->ladderMapper.toDto(Ladder));
	}

	@Override
	public void delete(Long id) {
		ladderRepository.deleteById(id);
		}

}

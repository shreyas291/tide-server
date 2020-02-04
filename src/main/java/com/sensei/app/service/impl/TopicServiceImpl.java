package com.sensei.app.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sensei.app.domain.Topic;
import com.sensei.app.repository.TopicRepository;
import com.sensei.app.service.TopicService;
import com.sensei.app.service.dto.TopicDTO;
import com.sensei.app.service.mapper.TopicMapper;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {
	
	private final TopicMapper topicMapper;
	
	private final TopicRepository topicRepository;
	
	

	public TopicServiceImpl(TopicMapper topicMapper, TopicRepository topicRepository) {
		this.topicMapper = topicMapper;
		this.topicRepository = topicRepository;
	}

	@Override
	public TopicDTO save(TopicDTO topicDTO) {
		Topic topic=topicMapper.toEntity(topicDTO);
		topic=topicRepository.save(topic);
	TopicDTO result=topicMapper.toDto(topic);
		return result;
	}

	@Override
	public Page<TopicDTO> findall(Pageable pageable) {
		Page<Topic> result=topicRepository.findAll(pageable);
		return result.map(Topic->topicMapper.toDto(Topic));
	}

	@Override
	public Optional<TopicDTO> findOne(Long id) {
		Optional<Topic> result=topicRepository.findById(id);
		return result.map(Topic->topicMapper.toDto(Topic));
	}

	@Override
	public void delete(Long id) {
		topicRepository.deleteById(id);
		
	}

}

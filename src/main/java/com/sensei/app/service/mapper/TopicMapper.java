package com.sensei.app.service.mapper;

import org.mapstruct.Mapper;

import com.sensei.app.domain.Topic;
import com.sensei.app.service.dto.TopicDTO;

@Mapper(componentModel = "spring", uses = {})
public interface TopicMapper extends EntityMapper<TopicDTO,Topic>{
	default Topic fromId(Long id) {
        if (id == null) {
            return null;
        }
        Topic topic = new Topic();
        topic.setId(id);
        return topic;
    }
}

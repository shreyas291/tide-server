package com.sensei.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.Topic;

public interface TopicRepository extends JpaRepository<Topic,Long>{

}

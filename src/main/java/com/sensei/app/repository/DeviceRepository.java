package com.sensei.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensei.app.domain.Device;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

}

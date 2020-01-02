package com.sensei.app.web.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

/**
 * @author HP
 *
 */
@RestController
@RequestMapping("/api")
public class DeviceResource {

    private final Logger log = LoggerFactory.getLogger(DeviceResource.class);
    
    public DeviceResource() {
    	
    }

    /**
     * GET /devices : get all devices.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all users
     */
    @GetMapping("/devices")
    @Timed
    public String Sample() {
        return "Hi";
    }
}

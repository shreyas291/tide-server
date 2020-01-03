package com.sensei.app.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceResource {
   // private final DeviceService deviceService;

//    @Autowired
//    public DeviceResource(DeviceService deviceService) {
//        this.deviceService = deviceService;
//    }

    /**
     * GET /devices : get all devices.
     *
     * @return the ResponseEntity with status 200 (OK) and with body all users
     */
    @GetMapping("/hi")
    @Timed
    public String Sample() {
        return "Hello";
    }

    /**
     * GET /devices : get all devices.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all users
     */
//    @GetMapping("/devices")
//    @Timed
//    public ResponseEntity<List<DeviceDTO>> getAllDevices(Pageable pageable) {
//        final Page<DeviceDTO> page = deviceService.getAllManagedDevices(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/devices");
//        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//    }
//
//    @PostMapping("/modifyDevice")
//    @Timed
//    public ResponseEntity<DeviceDTO> addDevice(@Valid @RequestBody DeviceDTO deviceDTO)  {
//        return new ResponseEntity<>(deviceService.addDevice(deviceDTO), HttpStatus.OK);
//    }
}

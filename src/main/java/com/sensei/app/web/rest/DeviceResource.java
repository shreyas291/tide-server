package com.sensei.app.web.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;
import com.sensei.app.service.DeviceService;
import com.sensei.app.service.dto.DeviceDTO;
import com.sensei.app.service.dto.StudentDTO;
import com.sensei.app.service.impl.DeviceServiceImpl;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

import javax.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DeviceResource {
	
	private final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);

    private final DeviceService deviceService;
    
    private static final String ENTITY_NAME = "Device";


 
    public DeviceResource(DeviceService deviceService) {
		super();
		this.deviceService = deviceService;
	}

	/**
     * GET /devices : get all devices.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all users
     */
    @GetMapping("/device")
    @Timed    
    public ResponseEntity<List<DeviceDTO>> getAllDevices(@ApiParam Pageable pageable){
    	log.debug("REST request to get device : {}", pageable);
    	 Page<DeviceDTO> page = deviceService.findall(pageable);
    	  HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/devices");
    	  return new ResponseEntity<>(page.getContent(),headers,HttpStatus.OK);
    	
    }
    
    @GetMapping("/device/{id}")
    @Timed    
    public ResponseEntity<Optional<DeviceDTO>> getDevicesById(@PathVariable Long id){
    	Optional<DeviceDTO> data=deviceService.findOne(id);
    	return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, data.get().getId().toString()))
				.body(data); 
    }
    
    
    /**
	 * POST /saveDevice : Create a new Device.
	 *
	 * @param referenceCodeDTO
	 *            the referenceCodeDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new referenceCodeDTO, or with status 400 (Bad Request) if the
	 *         referenceCode has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
    
    @PostMapping("/device")
	@Timed
	public ResponseEntity<DeviceDTO> create( @RequestBody DeviceDTO deviceDTO)
			throws URISyntaxException {
		log.debug("REST request to save Device : {}", deviceDTO);
		if (deviceDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new device cannot already have an ID")).body(null);
		}
		DeviceDTO result = deviceService.save(deviceDTO);
		return ResponseEntity.created(new URI("/api/device/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}
	
    
    
    /**
	 * DELETE /Device/:id : delete the "id" Device.
	 *
	 * @param id
	 *            the id of the referenceCodeDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
    @DeleteMapping("/device/{id}")
    @Timed
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id){
    	log.debug("REST request to delete Device : {}", id);
    	deviceService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    	
    	
    }
    /**
	 * PUT /reference-codes : Updates an existing Device.
	 *
	 * @param DeviceDTO
	 *            the DEviceDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         DEviceDTO, or with status 400 (Bad Request) if the
	 *         DEviceDTO is not valid, or with status 500 (Internal
	 *         Server Error) if the DEviceDTO couldnt be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
    @PutMapping("/device")
	@Timed
	public ResponseEntity<DeviceDTO> updateDevice(@Valid @RequestBody DeviceDTO deviceDTO)
			throws URISyntaxException {
		log.debug("REST request to update Device : {}", deviceDTO);
	if (deviceDTO.getId() == null) {
		return create(deviceDTO);
		}
	Optional<DeviceDTO> device=deviceService.findOne(deviceDTO.getId());
	deviceDTO.setVersion(device.get().getVersion());
		DeviceDTO result = deviceService.save(deviceDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deviceDTO.getId().toString()))
				.body(result);
	}
    
    
}

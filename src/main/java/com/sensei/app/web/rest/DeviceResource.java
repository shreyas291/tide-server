package com.sensei.app.web.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;
import com.sensei.app.service.DeviceService;
import com.sensei.app.service.dto.ApplicationStatusDTO;
import com.sensei.app.service.dto.DeviceDTO;
import com.sensei.app.service.dto.ErrorCode;
import com.sensei.app.service.dto.ReferenceCodeDTO;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceResource {
	private final Logger log = LoggerFactory.getLogger(ReferenceCodeResource.class);

    private final DeviceService deviceService;
    private static final String ENTITY_NAME = "Device";

    
    public DeviceResource(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

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
    @GetMapping("/devices")
    @Timed    
    public ResponseEntity<List<DeviceDTO>> getAllDevices(Pageable pageable){
    	log.debug("REST request to get device : {}", pageable);
    	final Page<DeviceDTO> page = deviceService.getAllManagedDevices(pageable);
    	  HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/devices");
    	  return new ResponseEntity<>(page.getContent(),headers,HttpStatus.OK);
    	  
    	
    	
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
    
    @PostMapping("/saveDevice")
    @Timed
    public ResponseEntity<DeviceDTO> addDevice(@Valid @RequestBody DeviceDTO deviceDTO) throws URISyntaxException {
      if (deviceDTO.getId() != null) {
    	  return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
					"A new referenceCode cannot already have an ID")).body(null);
      }
     
//      MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
//      headers.add("Name", "Deepak");
//      
//      ApplicationStatusDTO<DeviceDTO> appStatus = new ApplicationStatusDTO<DeviceDTO>(deviceService.addDevice(deviceDTO));
//      appStatus.setErrorCode(ErrorCode.Error_Success);
      
    	//return new ResponseEntity<ApplicationStatusDTO<DeviceDTO>>(appStatus, headers, HttpStatus.OK);
      DeviceDTO result = deviceService.addDevice(deviceDTO);
      return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deviceDTO.getId().toString()))
				.body(result);
    }
    
    
    /**
	 * DELETE /Device/:id : delete the "id" Device.
	 *
	 * @param id
	 *            the id of the referenceCodeDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
    @DeleteMapping("/deleteDevice/{id}")
    @Timed
    public ResponseEntity<Void> deleteDevice(@PathVariable Integer id){
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
    @PutMapping("/update-device")
	@Timed
	public ResponseEntity<DeviceDTO> updateDevice(@Valid @RequestBody DeviceDTO deviceDTO)
			throws URISyntaxException {
		log.debug("REST request to update ReferenceCode : {}", deviceDTO);
//		if (deviceDTO.getId() == null) {
//			return addDevice(deviceDTO).getBody();
//		}
		DeviceDTO result = deviceService.addDevice(deviceDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deviceDTO.getId().toString()))
				.body(result);
	}
    
    
}

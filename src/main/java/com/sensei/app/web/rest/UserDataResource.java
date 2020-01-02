package com.sensei.app.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.sensei.app.service.UserDataService;
import com.sensei.app.service.dto.UserDataDTO;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class UserDataResource {

	 private final Logger log = LoggerFactory.getLogger(UserResource.class);
	 
	 private static final String ENTITY_NAME = "user";
	 	
	 	@Autowired
	    private  UserDataService userService;
	 	
	 	
	 	 @GetMapping("/users/getData")
	     @Timed
	     public ResponseEntity<List<UserDataDTO>> getAllUsersData(Pageable pageable) {
	         final Page<UserDataDTO> page = userService.getAllUsers(pageable);
	         HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users/getData");
	         return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	     }
	 	 
	 	 @PostMapping("/userDataSaving")
	 	 @Timed
	 	 public ResponseEntity<UserDataDTO> saveUserData(@RequestBody UserDataDTO userDataDTO) {
	 		 UserDataDTO userData = userService.savingUserData(userDataDTO);
	 		 return ResponseEntity.ok().body(userData);
	 	 }
	 	 
	 	 @PutMapping("/userDataUpdating")
	 	 @Timed
	 	 public ResponseEntity<UserDataDTO> updateUserData(@RequestBody UserDataDTO userDataDTO) {
	 		 UserDataDTO userData = userService.updatingUserData(userDataDTO);
	 		 return ResponseEntity.ok().body(userData);
	 	 }
	 	 
	 	 @GetMapping("/getUserData/{id}")
	 	 @Timed
	 	 public ResponseEntity<UserDataDTO> getUserData(@PathVariable Long id) {
	 		 UserDataDTO userData = userService.getData(id);
	 		 return new ResponseEntity<>(userData, HttpStatus.OK);
	 	 }
	 	 
	 	 @DeleteMapping("/deleteUserData/{id}")
	     @Timed
	     public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	         log.debug("REST request to delete Asset : {}", id);
	         userService.delete(id);
	         return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	     }
}

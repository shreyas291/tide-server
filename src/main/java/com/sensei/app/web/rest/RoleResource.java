package com.sensei.app.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.sensei.app.repository.UserRepository;
import com.sensei.app.service.RoleService;
import com.sensei.app.service.dto.RoleDTO;
import com.sensei.app.web.rest.util.HeaderUtil;
import com.sensei.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing Roles.
 *
 * <p>This class accesses the Role entity, and needs to fetch its collection of authorities.</p>
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between Role and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * </p>
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the Role and the authorities, because people will
 * quite often do relationships with the Role, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our Roles'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages Roles, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>Another option would be to have a specific JPA entity graph to handle this case.</p>
 */
@RestController
@RequestMapping("/api")
public class RoleResource {

    private final Logger log = LoggerFactory.getLogger(RoleResource.class);

    private static final String ENTITY_NAME = "RoleManagement";

    private final RoleService roleService;
    
    private final UserRepository userRepository;

    public RoleResource(RoleService roleService, UserRepository userRepository) {
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    /**
     * POST  /roles  : Creates a new role.
     * <p>
     * Creates a new role
     * </p>
     *
     * @param roleDTO the role to create
     * @return the ResponseEntity with status 201 (Created) and with body the new role
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/roles")
    @Timed
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<RoleDTO> create(@RequestBody RoleDTO roleDTO) throws URISyntaxException {
        log.debug("REST request to save Role : {}", roleDTO);

        if (roleDTO.getId() != null) {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new role cannot already have an ID"))
                .body(null);
        // Lowercase the Role login before comparing with database
        }
        if(roleService.findOneByCode(roleDTO.getCode()) != null){
        	return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "roleCodeexists", "A new role cannot already have an roleCode"))
                    .body(null);
        }

        RoleDTO saveRoleDto = roleService.save(roleDTO);
        return new ResponseEntity<>(saveRoleDto, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * GET  /employees/:id : get the "id" employee.
     *
     * @param id the id of the employeeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/roles/{id}")
    @Timed
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<RoleDTO> getRole(@PathVariable Long id) {
        log.debug("REST request to get Role : {}", id);
        RoleDTO roleDTO = roleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(roleDTO));
    }
    
    /**
     * PUT  /roles : Updates an existing Role.
     *
     * @param roleDTO the Role to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated Role
     */
    @PutMapping("/roles")
    @Timed
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<RoleDTO> update(@RequestBody RoleDTO roleDTO) {
        log.debug("REST request to update Role : {}", roleDTO);
        RoleDTO role = roleService.save(roleDTO);
        return new ResponseEntity<RoleDTO>(role, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * GET  /Roles : get all Roles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all Roles
     */
    @GetMapping("/roles")
    @Timed
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<RoleDTO>> getAllRoles(@ApiParam Pageable pageable) {
        final Page<RoleDTO> page = roleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/Roles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /*
     * *
     * Get All Role For User Role DropDown
     */
    @GetMapping("/allRoles")
    @Timed
    public ResponseEntity<List<RoleDTO>> getAllRolesForDropDown() {
    	List<RoleDTO> roleDto = roleService.getAllRoles();
    	return new ResponseEntity<List<RoleDTO>>(roleDto, HttpStatus.OK);
    }
   
    /**
     * DELETE /roles/:id : delete the "id" Role.
     *
     * @param id of the Role to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/roles/{id}")
    @Timed
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        log.debug("REST request to delete Role: {}", id);
        RoleDTO roleDTO = roleService.findOne(id);
        if(roleDTO != null && userRepository.findOneByRole(roleDTO.getCode()).isPresent()){
        	return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "roleCodeInUse", roleDTO.getCode()+" is used by one or more users"))
                    .body(null);
        }
        roleService.delete(roleDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "RoleManagement.deleted", String.valueOf(id))).build();
    }
}

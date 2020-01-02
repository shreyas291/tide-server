    package com.sensei.app.service.impl;

    import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sensei.app.domain.RoleAuthority;
import com.sensei.app.repository.RoleAuthorityRepository;
import com.sensei.app.service.RoleAuthorityService;
import com.sensei.app.service.dto.RoleAuthorityDTO;
import com.sensei.app.service.mapper.RoleAuthorityMapper;

    /**
     * Service Implementation for managing Employee.
     */
    @Service
    @Transactional
    public class RoleAuthorityServiceImpl implements RoleAuthorityService{

//        private final Logger log = LoggerFactory.getLogger(RoleAuthorityServiceImpl.class);

        private final RoleAuthorityRepository roleAuthorityRepository;

        private final RoleAuthorityMapper roleAuthorityMapper;


        public RoleAuthorityServiceImpl(RoleAuthorityRepository roleAuthorityRepository, RoleAuthorityMapper roleAuthorityMapper) {
            this.roleAuthorityRepository = roleAuthorityRepository;
            this.roleAuthorityMapper = roleAuthorityMapper;
        }


        @Override
        public List<RoleAuthorityDTO> save(List<RoleAuthorityDTO> roleAuthorities) {
            List<RoleAuthority> authorities = roleAuthorityRepository.saveAll(roleAuthorityMapper.toEntity(roleAuthorities));
            return roleAuthorityMapper.toDto(authorities);
        }

        @Override
        public Page<RoleAuthorityDTO> findAll(Pageable pageable) {
            Page<RoleAuthority> roleAuthorities = roleAuthorityRepository.findAll(pageable);
            return roleAuthorities.map(roleAuthority -> roleAuthorityMapper.toDto(roleAuthority));
        }

        @Override
        public RoleAuthorityDTO findOne(Long id) {
        	Optional<RoleAuthorityDTO> roleAuthorityDTO = roleAuthorityRepository.findById(id)
                    .map(roleAuthorityMapper::toDto);
			return roleAuthorityDTO.get();
        	
        }

        @Override
        public void delete(Long id) {
            roleAuthorityRepository.deleteById(id);
        }
        
        @Override
        public List<RoleAuthority> findByRoleCode(String roleCode){
        	return roleAuthorityRepository.findByRoleCode(roleCode);
        }
    }

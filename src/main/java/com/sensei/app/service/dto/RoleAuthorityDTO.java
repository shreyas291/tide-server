package com.sensei.app.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by mohit.
 */
public class RoleAuthorityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

    @NotNull
    private String roleCode;

    @NotNull
    private String authorityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
}

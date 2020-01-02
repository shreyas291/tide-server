package com.sensei.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "role_authority")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RoleAuthority extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 0, max = 50)
    @Column(length = 50, name = "role_code")
    private String roleCode;

    @NotNull
    @Size(min = 0, max = 50)
    @Column(length = 50, name = "authority_name")
    private String authorityName;


    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String role_code) {
        this.roleCode
            = role_code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RoleAuthority authority = (RoleAuthority) o;

        if (roleCode != null ? !roleCode.equals(authority.roleCode) : authority.roleCode != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return roleCode != null ? roleCode.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RoleAuthority{" +
            "role_code='" + roleCode + '\'' +
            "}";
    }
}

package com.sensei.app.service.dto;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import com.sensei.app.domain.Branch;

/**
 * A DTO for the Branchset entity.
 */
public class BranchsetDTO {

    private Long id;

    private Integer version;

    private String name;

    private String code;
    
    private List<Long> branchIds;
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;
    
    private List<Branch> branches ;
    
    
	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public List<Long> getBranchIds() {
		return branchIds;
	}

	public void setBranchIds(List<Long> branchIds) {
		this.branchIds = branchIds;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BranchsetDTO branchsetDTO = (BranchsetDTO) o;
        if (branchsetDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), branchsetDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BranchsetDTO{" +
            "id=" + getId() +
            ", version=" + getVersion() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}

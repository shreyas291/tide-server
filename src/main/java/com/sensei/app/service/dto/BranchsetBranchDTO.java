package com.sensei.app.service.dto;

import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the BranchsetBranch entity.
 */
public class BranchsetBranchDTO {

    private Long id;

    private Integer version;

    private Long branchId;

    private Long branchsetId;
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;
    
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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getBranchsetId() {
		return branchsetId;
	}

	public void setBranchsetId(Long branchsetId) {
		this.branchsetId = branchsetId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BranchsetBranchDTO branchsetBranchDTO = (BranchsetBranchDTO) o;
        if (branchsetBranchDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), branchsetBranchDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "BranchsetBranchDTO [id=" + id + ", version=" + version + ", branchId=" + branchId + ", branchsetId="
				+ branchsetId + "]";
	}
   
}

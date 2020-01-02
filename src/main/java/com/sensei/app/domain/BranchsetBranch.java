package com.sensei.app.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BranchsetBranch.
 */
@Entity
@Table(name = "branchset_branch")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BranchsetBranch extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Version
    private Integer version;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "branchset_id")
    private Long branchsetId;

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

    public BranchsetBranch branchId(Long branchId) {
        this.branchId = branchId;
        return this;
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
        BranchsetBranch branchsetBranch = (BranchsetBranch) o;
        if (branchsetBranch.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), branchsetBranch.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "BranchsetBranch [id=" + id + ", version=" + version + ", branchId=" + branchId + ", branchsetId="
				+ branchsetId + "]";
	}

}

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

@Entity 
@Table(name="milestones")
public class Milestones extends AbstractAuditingEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 
	 @Version
	 private int version;
	 
	 @NotNull
	 @Column(name="baseline")
	 private int baseline;
	 
	 @Column(name="description")
	 private String description;
	 
	 @Column(name="end")
	 private Long end;
	 
	 @NotNull
	 @Column(name="milestone_id")
	 private Long milestoneId;

	 @Column(name="start")
	 private Long start;

	 @NotNull
	 @Column(name="status")
	 private String status;

	 @NotNull
	 @Column(name="ladder_id")
	 private int ladderId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getBaseline() {
		return baseline;
	}

	public void setBaseline(int baseline) {
		this.baseline = baseline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public Long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLadderId() {
		return ladderId;
	}

	public void setLadderId(int ladderId) {
		this.ladderId = ladderId;
	}
	
	@Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Milestones milestones = (Milestones) o;
        if (milestones.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), milestones.getId());
    }
	 
}

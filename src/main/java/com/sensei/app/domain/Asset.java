package com.sensei.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Asset.
 */
@Entity
@Table(name = "asset")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "asset")
public class Asset extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "asset_type", nullable = false)
    private String assetType;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "procurement_date")
    private LocalDate procurementDate;

    @NotNull
    @Column(name = "asset_id", nullable = false)
    private String assetId;

    @Column(name = "validity_available")
    private Boolean validityAvailable;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetType() {
        return assetType;
    }

    public Asset assetType(String assetType) {
        this.assetType = assetType;
        return this;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Asset price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getProcurementDate() {
        return procurementDate;
    }

    public Asset procurementDate(LocalDate procurementDate) {
        this.procurementDate = procurementDate;
        return this;
    }

    public void setProcurementDate(LocalDate procurementDate) {
        this.procurementDate = procurementDate;
    }

    public String getAssetId() {
        return assetId;
    }

    public Asset assetId(String assetId) {
        this.assetId = assetId;
        return this;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Boolean isValidityAvailable() {
        return validityAvailable;
    }

    public Asset validityAvailable(Boolean validityAvailable) {
        this.validityAvailable = validityAvailable;
        return this;
    }

    public void setValidityAvailable(Boolean validityAvailable) {
        this.validityAvailable = validityAvailable;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Asset asset = (Asset) o;
        if (asset.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), asset.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Asset{" +
            "id=" + getId() +
            ", assetType='" + getAssetType() + "'" +
            ", price=" + getPrice() +
            ", procurementDate='" + getProcurementDate() + "'" +
            ", assetId='" + getAssetId() + "'" +
            ", validityAvailable='" + isValidityAvailable() + "'" +
            "}";
    }
}

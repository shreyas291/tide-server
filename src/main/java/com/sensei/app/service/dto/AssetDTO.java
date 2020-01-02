package com.sensei.app.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Asset entity.
 */
public class AssetDTO implements Serializable {

    private Long id;

    @NotNull
    private String assetType;

    private BigDecimal price;

    private LocalDate procurementDate;

    @NotNull
    private String assetId;

    private Boolean validityAvailable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getProcurementDate() {
        return procurementDate;
    }

    public void setProcurementDate(LocalDate procurementDate) {
        this.procurementDate = procurementDate;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Boolean isValidityAvailable() {
        return validityAvailable;
    }

    public void setValidityAvailable(Boolean validityAvailable) {
        this.validityAvailable = validityAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AssetDTO assetDTO = (AssetDTO) o;
        if (assetDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetDTO{" +
            "id=" + getId() +
            ", assetType='" + getAssetType() + "'" +
            ", price=" + getPrice() +
            ", procurementDate='" + getProcurementDate() + "'" +
            ", assetId='" + getAssetId() + "'" +
            ", validityAvailable='" + isValidityAvailable() + "'" +
            "}";
    }
}

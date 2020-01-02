package com.sensei.app.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;

import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the Asset entity. This class is used in AssetResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /assets?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AssetCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter assetType;

    private BigDecimalFilter price;

    private LocalDateFilter procurementDate;

    private StringFilter assetId;

    private BooleanFilter validityAvailable;

    public AssetCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getAssetType() {
        return assetType;
    }

    public void setAssetType(StringFilter assetType) {
        this.assetType = assetType;
    }

    public BigDecimalFilter getPrice() {
        return price;
    }

    public void setPrice(BigDecimalFilter price) {
        this.price = price;
    }

    public LocalDateFilter getProcurementDate() {
        return procurementDate;
    }

    public void setProcurementDate(LocalDateFilter procurementDate) {
        this.procurementDate = procurementDate;
    }

    public StringFilter getAssetId() {
        return assetId;
    }

    public void setAssetId(StringFilter assetId) {
        this.assetId = assetId;
    }

    public BooleanFilter getValidityAvailable() {
        return validityAvailable;
    }

    public void setValidityAvailable(BooleanFilter validityAvailable) {
        this.validityAvailable = validityAvailable;
    }

    @Override
    public String toString() {
        return "AssetCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (assetType != null ? "assetType=" + assetType + ", " : "") +
                (price != null ? "price=" + price + ", " : "") +
                (procurementDate != null ? "procurementDate=" + procurementDate + ", " : "") +
                (assetId != null ? "assetId=" + assetId + ", " : "") +
                (validityAvailable != null ? "validityAvailable=" + validityAvailable + ", " : "") +
            "}";
    }

}

package com.sensei.app.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ReferenceCode.
 */
@Entity
@Table(name = "reference_code")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReferenceCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "version", nullable = false)
    private Integer version = 1;

    @NotNull
    @Column(name = "classifier", nullable = false)
    private String classifier;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "parent_classifier")
    private String parentClassifier;

    @Column(name = "parent_reference_code")
    private String parentReferenceCode;

    @Column(name = "status")
    private Integer status;

    @Column(name = "field_1")
    private String field1;

    @Column(name = "field_2")
    private String field2;

    @Column(name = "field_3")
    private String field3;

    @Column(name = "field_4")
    private String field4;

    @Column(name = "field_5")
    private String field5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public ReferenceCode version(Integer version) {
        this.version = 1;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = 1;
    }

    public String getClassifier() {
        return classifier;
    }

    public ReferenceCode classifier(String classifier) {
        this.classifier = classifier;
        return this;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public String getName() {
        return name;
    }

    public ReferenceCode name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public ReferenceCode code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentClassifier() {
        return parentClassifier;
    }

    public ReferenceCode parentClassifier(String parentClassifier) {
        this.parentClassifier = parentClassifier;
        return this;
    }

    public void setParentClassifier(String parentClassifier) {
        this.parentClassifier = parentClassifier;
    }

    public String getParentReferenceCode() {
        return parentReferenceCode;
    }

    public ReferenceCode parentReferenceCode(String parentReferenceCode) {
        this.parentReferenceCode = parentReferenceCode;
        return this;
    }

    public void setParentReferenceCode(String parentReferenceCode) {
        this.parentReferenceCode = parentReferenceCode;
    }

    public Integer getStatus() {
        return status;
    }

    public ReferenceCode status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getField1() {
        return field1;
    }

    public ReferenceCode field1(String field1) {
        this.field1 = field1;
        return this;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public ReferenceCode field2(String field2) {
        this.field2 = field2;
        return this;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public ReferenceCode field3(String field3) {
        this.field3 = field3;
        return this;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public ReferenceCode field4(String field4) {
        this.field4 = field4;
        return this;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public ReferenceCode field5(String field5) {
        this.field5 = field5;
        return this;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReferenceCode referenceCode = (ReferenceCode) o;
        if (referenceCode.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, referenceCode.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ReferenceCode{" +
            "id=" + id +
            ", version='" + version + "'" +
            ", classifier='" + classifier + "'" +
            ", name='" + name + "'" +
            ", code='" + code + "'" +
            ", parentClassifier='" + parentClassifier + "'" +
            ", parentReferenceCode='" + parentReferenceCode + "'" +
            ", status='" + status + "'" +
            ", field1='" + field1 + "'" +
            ", field2='" + field2 + "'" +
            ", field3='" + field3 + "'" +
            ", field4='" + field4 + "'" +
            ", field5='" + field5 + "'" +
            '}';
    }
}

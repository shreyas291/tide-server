package com.sensei.app.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

/**
 * A DTO for the ReferenceCode entity.
 */
public class ReferenceCodeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer version;

    @NotBlank
    private String classifier;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    private String parentClassifier;

    private String parentReferenceCode;

    private Integer status;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

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

    public void setVersion(Integer version) {
        this.version = version;
    }
    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
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
    public String getParentClassifier() {
        return parentClassifier;
    }

    public void setParentClassifier(String parentClassifier) {
        this.parentClassifier = parentClassifier;
    }
    public String getParentReferenceCode() {
        return parentReferenceCode;
    }

    public void setParentReferenceCode(String parentReferenceCode) {
        this.parentReferenceCode = parentReferenceCode;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }
    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }
    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }
    public String getField5() {
        return field5;
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

        ReferenceCodeDTO referenceCodeDTO = (ReferenceCodeDTO) o;

        if ( ! Objects.equals(id, referenceCodeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ReferenceCodeDTO{" +
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

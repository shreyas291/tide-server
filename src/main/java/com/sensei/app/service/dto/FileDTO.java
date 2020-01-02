package com.sensei.app.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by prashanth on 16/5/17.
 */

public class FileDTO {

    private Long id;

    private String fileName;

    private Long fileSize;

    private String type;

    private String uploaderId;

    public String getUploaderId(){
        return uploaderId;
    }

    public void setUploaderId(String uploaderId){
        this.uploaderId = uploaderId;
    }

    @JsonIgnore
    private byte[] fileContent;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}

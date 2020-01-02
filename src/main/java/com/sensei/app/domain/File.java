package com.sensei.app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by prashanth on 16/5/17.
 */
@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", length = 1024)
    private String fileName;

    @NotNull
    @Column(name = "size")
    private Long fileSize;

    @NotNull
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "path")
    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

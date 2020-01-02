package com.sensei.app.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sensei.app.service.dto.FileDTO;

/**
 * Created by prashanth on 16/5/17.
 */
public interface FileService {
    public List<FileDTO> saveFiles(List<FileDTO> files);
    public void deleteFile(Long fileId);
    public void streamFile(Long fileId, HttpServletResponse response);
    public void downloadFileTemplate(String fileType,HttpServletResponse response) throws IOException;
    public void generateSampleExcel(HttpServletResponse response);
    public void generateSamplePdf(HttpServletResponse response);
}

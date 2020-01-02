package com.sensei.app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.codahale.metrics.annotation.Timed;
import com.sensei.app.config.ApplicationProperties;
import com.sensei.app.domain.File;
import com.sensei.app.repository.FileRepository;
import com.sensei.app.service.FileService;
import com.sensei.app.service.dto.FileDTO;

/**
 * Created by prashanth on 16/5/17.
 */
@Service
@ConditionalOnProperty(name="application.files.storageType", havingValue="cloud")
public class S3FileServiceImpl implements FileService {

    private static final Logger log = LoggerFactory.getLogger(S3FileServiceImpl.class);
    private final FileRepository fileRepository;
    private final ApplicationProperties applicationProperties;
    private final AmazonS3 s3Client;
    private final static String BUCKET_NAME = "test";

    public S3FileServiceImpl(FileRepository fileRepository, ApplicationProperties applicationProperties, AmazonS3 s3Client){
        this.fileRepository = fileRepository;
        this.applicationProperties = applicationProperties;
        this.s3Client = s3Client;
    }

    @Override
    public List<FileDTO> saveFiles(List<FileDTO> files) {
        try {
            for (FileDTO dto : files) {
                File fileInfo = new File();
                String filePath = UUID.randomUUID().toString();
                fileInfo.setType(dto.getType());
                fileInfo.setFileName(dto.getFileName());
                fileInfo.setFileSize(dto.getFileSize());
                java.io.File fileToStore = new java.io.File(filePath);
                fileInfo.setPath(filePath + "/" + dto.getFileName());

                java.io.FileOutputStream fileOutputStream = new java.io.FileOutputStream(fileToStore);
                fileOutputStream.write(dto.getFileContent());
                s3Client.putObject(new PutObjectRequest(
                        BUCKET_NAME, filePath + "/" + fileInfo.getFileName(), fileToStore));
                fileOutputStream.close();
                fileRepository.saveAndFlush(fileInfo);
                dto.setId(fileInfo.getId());
            }
        }catch (Exception e){
            log.error("Error saving files to S3", e);
        }
        return files;
    }

    @Override
    @Timed
    public void deleteFile(Long fileId){
        File fileInfo = fileRepository.findOneById(fileId);

        if(fileInfo == null) {
            // throw new ProgramException("com.sensei.app.filemanagerservice.failedToFindFileWithId", fileId);
            return;
        }

        String fileAbsPath = fileInfo.getPath();
        try {
            s3Client.deleteObject(new DeleteObjectRequest(
                    BUCKET_NAME, fileAbsPath));
        }catch (Exception amazonServiceException){
            log.error("Error deleting file", amazonServiceException);
        }

        fileRepository.delete(fileInfo);
    }

    @Override
    public void streamFile(Long fileId, HttpServletResponse response) {
        File fileInfo = fileRepository.findOneById(fileId);

        if(fileInfo == null) {
        //    throw new ProgramException("com.sensei.app.filemanagerservice.failedToFindFileWithId", fileId);
            return;
        }

        S3Object s3object = null;

        try {
            s3object = s3Client.getObject(new GetObjectRequest(
                    BUCKET_NAME, fileInfo.getPath()));
        }catch (Exception e){
            log.error("Error getting file from s3", e);
        }
        response.setHeader("Content-Type", fileInfo.getType());

        if(fileInfo.getFileSize() != 0)
            response.setHeader("Content-Length", "" + fileInfo.getFileSize());
        response.setHeader("Content-Disposition", "attachment; filename=" + fileInfo.getFileName());

        try {
            org.apache.commons.io.IOUtils.copy(s3object.getObjectContent(), response.getOutputStream());
        }catch (IOException e) {
            log.warn("Unable to stream file with id:" + fileId + " message: "+ e.getMessage());
            // throw new ProgramException("com.sensei.app.filemanagerservice.failedToStreamFile", e, fileId);
        }
    }

	@Override
	public void downloadFileTemplate(String fileType, HttpServletResponse response) {
		// TODO Auto-generated method stub
		log.debug("abc");
	}

	@Override
	public void generateSampleExcel(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateSamplePdf(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
}

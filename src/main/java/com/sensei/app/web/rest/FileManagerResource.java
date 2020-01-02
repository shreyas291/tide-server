package com.sensei.app.web.rest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.codahale.metrics.annotation.Timed;
import com.sensei.app.config.ApplicationProperties;
import com.sensei.app.service.FileService;
import com.sensei.app.service.dto.FileDTO;

/**
 * Created by prashanth on 16/5/17.
 */
@RequestMapping("/api")
@RestController
public class FileManagerResource {
    private static Logger logger = LoggerFactory.getLogger(FileManagerResource.class);
    private final FileService fileService;
    private final ApplicationProperties applicationProperties;
    
    @Inject
    private Tika tika;
    
    public FileManagerResource(FileService fileService,ApplicationProperties applicationProperties){
        this.fileService = fileService;
        this.applicationProperties = applicationProperties;
    }

    @PostMapping("/file/upload")
    @Timed
//    @PreAuthorize("hasAuthority('ROLE_FILES')")
    public ResponseEntity<List<FileDTO>> uploadFile(StandardMultipartHttpServletRequest request){
        logger.info("Request to upload Files", request);
        List<FileDTO> dtos = new ArrayList<>();

        //get FileContentType
        List<String> contentTypes = applicationProperties.getAllowedFileContentTypes();
        for(MultipartFile file: request.getFiles("files")){
       		try{
       			String filetype = tika.detect(file.getBytes());
           		if(contentTypes.contains(filetype)) {
           			FileDTO dto = new FileDTO();
                    dto.setFileName(file.getOriginalFilename());
                    dto.setFileSize(file.getSize());
                    dto.setType(file.getContentType());
                    dto.setUploaderId(request.getParameter(file.getOriginalFilename()));
                    dto.setFileContent(file.getBytes());
                    dtos.add(dto);
       			}else {
       				return ResponseEntity.badRequest().header("Failure", "The file type is not allowed.").body(null);
     			}
       		}catch(IOException e) {
       			e.printStackTrace();
       		}
        }
        return new ResponseEntity<List<FileDTO>>(fileService.saveFiles(dtos), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/file/delete/{fileId}")
    @Timed
//    @PreAuthorize("hasAuthority('ROLE_FILES')")
    public ResponseEntity<Long> deleteFile(@PathVariable Long fileId){
        logger.info("Request to delete Files", fileId);
        fileService.deleteFile(fileId);
        return new ResponseEntity<Long>(new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/file/stream/{fileId}")
    @Timed
    public void streamFile(@PathVariable Long fileId, HttpServletResponse response){
        logger.info("Request to download Files", fileId);
//        if(SecurityUtils.isCurrentUserInRole("ROLE_FILES")) {
//            fileService.streamFile(fileId, response);
//        }
        fileService.streamFile(fileId, response);
    }
    
    @RequestMapping(value = "/_downloads/sampleFiles", method = RequestMethod.GET)
	@Timed
	public void downloadSampleFiles(@RequestParam(value="fileType")String fileType,HttpServletResponse response) throws URISyntaxException {
		try {
			fileService.downloadFileTemplate(fileType, response);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
    
    @RequestMapping(value = "/_downloads/generateExcel", method = RequestMethod.GET)
	@Timed
	public void generateSampleExcel(HttpServletResponse response) throws URISyntaxException {
		try {
			fileService.generateSampleExcel(response);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
    
    @RequestMapping(value = "/_downloads/generatePdf", method = RequestMethod.GET)
	@Timed
	public void generateSamplePdf(HttpServletResponse response) throws URISyntaxException {
		try {
			fileService.generateSamplePdf(response);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
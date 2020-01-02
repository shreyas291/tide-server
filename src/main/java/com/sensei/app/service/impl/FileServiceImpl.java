package com.sensei.app.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.codahale.metrics.annotation.Timed;
import com.google.common.io.Files;
import com.sensei.app.config.ApplicationProperties;
import com.sensei.app.domain.File;
import com.sensei.app.domain.User;
import com.sensei.app.repository.FileRepository;
import com.sensei.app.repository.UserRepository;
import com.sensei.app.service.FileService;
import com.sensei.app.service.dto.FileDTO;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;


/**
 * Created by prashanth on 16/5/17.
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
    private final FileRepository fileRepository;
    private final ApplicationProperties applicationProperties;
    private final UserRepository userRepository;
    private final DataSource dataSource;

    public FileServiceImpl(FileRepository fileRepository, ApplicationProperties applicationProperties,
    		UserRepository userRepository,DataSource dataSource){
        this.fileRepository = fileRepository;
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
        this.dataSource = dataSource;
    }

    @Override
    public List<FileDTO> saveFiles(List<FileDTO> files) {
        for(FileDTO dto: files) {
            File fileInfo = new File();
            String filePath = UUID.randomUUID().toString();
            fileInfo.setType(dto.getType());
            fileInfo.setFileName(dto.getFileName());
            fileInfo.setFileSize(dto.getFileSize());
            String currentBasePath = getBasePath();
            checkAndCreateComponentPath("", currentBasePath);
            filePath = createFilePath("", filePath, currentBasePath);
            java.io.File fileToStore = new java.io.File(filePath);
            fileInfo.setPath(filePath);

            try (FileOutputStream output = new FileOutputStream(fileToStore)) {
                output.write(dto.getFileContent());
            } catch (IOException e) {
                // throw new ProgramException("com.sensei.app.filemanagerservice.failedToSaveFile", e);
            }
            fileRepository.saveAndFlush(fileInfo);
            dto.setId(fileInfo.getId());
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
        java.io.File imageFile = new java.io.File(fileAbsPath);

        if(!imageFile.delete())
            // throw new ProgramException("com.sensei.app.filemanagerservice.unableToDelete", fileId);

        fileRepository.delete(fileInfo);
    }

    @Override
    public void streamFile(Long fileId, HttpServletResponse response) {
        File fileInfo = fileRepository.findOneById(fileId);

        if(fileInfo == null) {
        //    throw new ProgramException("com.sensei.app.filemanagerservice.failedToFindFileWithId", fileId);
            return;
        }

        java.io.File contentFile = new java.io.File(fileInfo.getPath());

        response.setHeader("Content-Type", fileInfo.getType());

        if(fileInfo.getFileSize() != 0)
            response.setHeader("Content-Length", "" + fileInfo.getFileSize());
        response.setHeader("Content-Disposition", "attachment; filename=" + fileInfo.getFileName());

        try {
            Files.copy(contentFile, response.getOutputStream());
        }catch (IOException e) {
            log.warn("Unable to stream file with id:" + fileId + " message: "+ e.getMessage());
            // throw new ProgramException("com.sensei.app.filemanagerservice.failedToStreamFile", e, fileId);
        }
    }
    
    @Override
	public void downloadFileTemplate(String fileType, HttpServletResponse response) throws IOException {
    	//reads the file from the project resource folder and downloads the file 
    	if("excel".equalsIgnoreCase(fileType)){
			try(InputStream inputStream = this.getClass().getResourceAsStream("/templates/SampleExcel.xlsx"))
			{
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				response.setHeader("Content-Disposition", "attachment; filename=SampleTemplate.xlsx"); 
				IOUtils.copy(inputStream, response.getOutputStream()); // writes the input stream data to output stream.
			} 
		}
    	//reads the file from outside the application(local folders). 
    	//The file path is specified in the application properties file
		else if("pdf".equalsIgnoreCase(fileType)){
			java.io.File file = new java.io.File(applicationProperties.getResourcePath()+"/SamplePdfFile.pdf");
			try(InputStream inputStream = new FileInputStream(file))
			{
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=SampleTemplate.pdf"); 
				IOUtils.copy(inputStream, response.getOutputStream());
			}
		}
	}
    
    @Override
	public void generateSampleExcel(HttpServletResponse response) {
    	//download the sample excel file using sample user data from DB.
    	//get the list of users for the database
    	List<User> userList = userRepository.findAll();
    	
    	//create XSSF Workbook and save the details in the excel sheet
    	try(XSSFWorkbook wb = new XSSFWorkbook();){
    		XSSFSheet sheet = wb.createSheet("Users");
    		XSSFRow row = sheet.createRow(0);
			sheet.createFreezePane(0,1);
    		
    		//create a cell style and font for headers to look better (Optional)
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
			font.setFontHeightInPoints((short) 10);
			font.setBold(true);
			style.setFont(font);
			
			//create the header cells
			XSSFCell cell = row.createCell(0, Cell.CELL_TYPE_STRING);
			cell.setCellValue("Login");
			cell.setCellStyle(style);
			
			cell = row.createCell(1, Cell.CELL_TYPE_STRING);
			cell.setCellValue("First Name");
			cell.setCellStyle(style);

			cell = row.createCell(2, Cell.CELL_TYPE_STRING);
			cell.setCellValue("Last Name");
			cell.setCellStyle(style);

			cell = row.createCell(3, Cell.CELL_TYPE_STRING);
			cell.setCellValue("Email");
			cell.setCellStyle(style);
			
			//create a cell and enter the user data into sheet
			for(User user: userList){
				int lastRowNum = sheet.getLastRowNum();
				XSSFRow dataRow = sheet.createRow(lastRowNum + 1);
				XSSFCell dataCell = null;
				dataCell = dataRow.createCell(0, Cell.CELL_TYPE_STRING);
				dataCell.setCellValue(user.getLogin());
				dataCell = dataRow.createCell(1, Cell.CELL_TYPE_STRING);
				dataCell.setCellValue(user.getFirstName());
				dataCell = dataRow.createCell(2, Cell.CELL_TYPE_STRING);
				dataCell.setCellValue(user.getLastName());
				dataCell = dataRow.createCell(3, Cell.CELL_TYPE_STRING);
				dataCell.setCellValue(user.getEmail());
			}
			
			//resize the cells length
			for(int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
				sheet.autoSizeColumn(columnIndex);
			}
			response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");
			wb.write(response.getOutputStream());
    	}
    	catch (Exception e) {
			log.error(e.getMessage());
		}
	}
    
    @Override
	public void generateSamplePdf(HttpServletResponse response) {
		// Download the sample pdf file generated form sample.
		// This report shows, how to pass parameters and connection to jasper report to
		// get the required details.
		// The title parameter is for display the parameter value i report and
		// connection parameter for execute query i Jasper.

		JasperPrint jasperPrint = new JasperPrint();
		try (Connection connection = dataSource.getConnection();
				InputStream jasperStream = this.getClass().getResourceAsStream("/templates/reports/samplePdfTemplate.jasper")) {
			Map<String, Object> params = new HashMap<>();
			params.put("Title", "Sample Pdf Report");
			params.put("REPORT_CONNECTION", connection);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=samplePdfTemplate.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

    private void checkAndCreateComponentPath(String componentPath, String currentBasePath) {
        java.io.File componentFile = new java.io.File(currentBasePath + java.io.File.separator + componentPath);
        if (!componentFile.exists()) {
            if(!componentFile.mkdirs()){

            }
        }
    }

    private String createFilePath(String componentPath, String filePath, String currentBasePath) {
        filePath = currentBasePath + java.io.File.separator + componentPath + java.io.File.separator + filePath;
        return filePath;
    }

    private String getBasePath() {
        List<String> storagePaths = applicationProperties.getStoragePaths();
        if(storagePaths.size() == 1) return storagePaths.get(0);
        return storagePaths.get((int) Math.abs(Math.random() % storagePaths.size()));
    }
}

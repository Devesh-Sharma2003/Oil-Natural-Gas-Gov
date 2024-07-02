package com.ongc.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.apache.poi.util.StringUtil;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ongc.dto.responseDto.FileDto;

import org.springframework.util.StringUtils;



@Service
public class AttachmentService {
	
	private String docPath = File.separator + "home" +File.separator + "kpmg" + File.separator + "jarwar" +
			File.separator+ "ongcDocs" +File.separator;
	
	private String tempPath = "ongcTempDocs";
	
	static private String hashPassword = "U2FsdGVkX1/rGDTQ5/vtQ9D6RWYsf5+yUj6+mX3I8Qs=";
	
	AES256TextEncryptor aesTextEncryptor = new AES256TextEncryptor();
	
	public AttachmentService() {
		aesTextEncryptor.setPassword(hashPassword);
	}
	
	
	public String getFileName(MultipartFile file, String identifier) {
		return identifier + "-" + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
	}
	
	public String uploadFile(MultipartFile uploadFile) {
		String fileName =StringUtils.cleanPath(uploadFile.getOriginalFilename()).replaceAll("\\s", "");
		String pathDirectory = System.getProperty("java.io.tmpdir");
		pathDirectory+=tempPath;
		long currentTime = System.currentTimeMillis();
		Path path = Paths.get(pathDirectory + String.valueOf(currentTime)+fileName);
		
		try {
			Files.createDirectories(Paths.get(pathDirectory+File.separator));
			Files.copy(uploadFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return aesTextEncryptor.encrypt(path.toString());
	}
			
	
	public String uploadOnRealPath(FileDto fileDto) throws FileNotFoundException{
		String tempDocPath = this.decryptUrl(fileDto.getVirtualPath());
		
		if(new File(tempDocPath).isFile()) {
			String filePath = docPath +File.separator;
			LocalDate date = LocalDate.now();
			
			filePath += fileDto.getAppNo() + File.separator +date.getYear() + File.separator + date.getMonthValue()
			+ File.separator
			+date.getDayOfMonth() + File.separator;
			
			try {
				Files.createDirectories(Paths.get(filePath + File.separator));
				
				filePath += tempDocPath.substring(tempDocPath.lastIndexOf(File.separator)+1);
				Path temp = Files.move(Paths.get(tempDocPath), Paths.get(filePath));
				if(temp!=null) {
					return aesTextEncryptor.encrypt(filePath);
				}
			}catch(Exception e) {
				e.getMessage();
			}
			return aesTextEncryptor.encrypt(filePath);
		}else {
			throw new FileNotFoundException("file already moved");
		}
	}
	
	public String decryptUrl(String encriptPath) {
		return aesTextEncryptor.decrypt(encriptPath);
	}
	
	public HashMap<String, String> uploadFiles(List<MultipartFile> uploadFiles){
		HashMap<String,String> pathResponse = new HashMap<>();
		String encodePath="";
		String fileName="";
		String pathDirectory = System.getProperty("java.io.tmpdir");
		pathDirectory+=tempPath;
		
		for(MultipartFile uploadFile:uploadFiles) {
			fileName = StringUtils.cleanPath(uploadFile.getOriginalFilename()).replaceAll("//s", "");
			long currentTime = System.currentTimeMillis();
			Path path = Paths.get(pathDirectory + String.valueOf(currentTime)+fileName);
			
			try {
				Files.createDirectories(Paths.get(pathDirectory+File.separator));
				Files.copy(uploadFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}catch(IOException e) {
				e.printStackTrace();
			}
			encodePath = aesTextEncryptor.encrypt(path.toString());
			
			pathResponse.put(fileName, encodePath);
		}
		return pathResponse;
	}
}

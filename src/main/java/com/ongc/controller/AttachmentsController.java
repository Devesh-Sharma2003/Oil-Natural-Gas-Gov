package com.ongc.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ongc.dto.responseDto.ApiResponse;
import com.ongc.service.AttachmentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins="*",allowedHeaders = "*")
@RequestMapping("/doc")
public class AttachmentsController {
	
	@Autowired
	private AttachmentService attachmentService;
	
	
	
	@PostMapping("/uploadAttachment")
	public ApiResponse<Object> uploadAttachment(@RequestBody List<MultipartFile> files){
		return new ApiResponse<>(true,"success",attachmentService.uploadFiles(files),HttpStatus.OK.value());
	}
	
	
	
	
	@PostMapping("/download")
	public ResponseEntity<?> downloadDocument(@RequestParam("filepath") String filePath){
		String actualPath = attachmentService.decryptUrl(filePath);
		Path path = Paths.get(actualPath);
		Resource resource = null;
		
		try {
			resource = new UrlResource(path.toUri());
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename() +"\"" )
				.body(resource);
	}
}

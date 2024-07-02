package com.ongc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ongc.dto.requestDto.SearchFilterDto;
import com.ongc.service.SearchByFilterService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SearchByFilterController {
	
	@Autowired
	private SearchByFilterService searchByFilterService;
	
	public ResponseEntity<?> searchPolicy(@RequestBody SearchFilterDto searchFilterDto){
		return new ResponseEntity<>(searchByFilterService.getPolicyBySearch(searchFilterDto), HttpStatus.OK);
	}

}

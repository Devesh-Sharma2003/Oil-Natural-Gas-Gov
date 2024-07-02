package com.ongc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongc.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DashBoardController {
	
	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("recentAmendments")
	public ResponseEntity<?> getRecentAmendments(){
		return new ResponseEntity<>(dashboardService.getAmendmentList(), HttpStatus.OK);
	}
	
	public ResponseEntity<?> getAmendmentDocsBy(@PathVariable Long amendmentId){
		return new ResponseEntity<>(dashboardService.getAmendDocsBy(amendmentId),HttpStatus.OK);
	}
}

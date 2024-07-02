package com.ongc.controller;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ongc.config.AesUtil;
import com.ongc.config.JwtTokenUtil;
import com.ongc.dto.requestDto.JwtRequest;
import com.ongc.model.UserMstModel;
import com.ongc.service.JwtUserDetailsService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*",allowedHeaders = "*")
public class JwtAuthenticationController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService; 
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, 
			HttpServletRequest req) throws Exception{
		try {
			
			System.out.println(authenticationRequest.getUsername()+" : "+authenticationRequest.getPassword()+" : "
		+" : "+authenticationRequest.getPassword2());
			
			
			authenticate(authenticationRequest.getUsername(), 
					authenticationRequest.getPassword(), 
					authenticationRequest.getPassword2());
			
			userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());
			
			HashMap<String,Object> result = new HashMap<>(); 
			result.put("token", token);
			result.put("userdetails", userDetailsService.getLoginUserData(authenticationRequest.getUsername()));
			result.put("status", true);
			
			return ResponseEntity.ok(result);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			HashMap<String,Object> result = new HashMap<>(); 
			result.put("status", false);
			result.put("message", "Invalid Username or Password");
			
			return ResponseEntity.status(201).body(result).ok(result);
		}
	}
	
	private void authenticate(String username, String password, String salt) throws Exception{
		try {
			UserMstModel userData = userDetailsService.getUserDetailsByUserName(username);
			if(userData == null) {
				throw new Exception("INVALID_CREDENTIALS");
			}
			
			AesUtil aes = new AesUtil();
			String actualPassword = aes.decrypt(userData.getPassword());
			
			String hashedPassword = getSecurePassword(actualPassword, salt);
			
			if(!hashedPassword.equals(password)) {
				throw new Exception("INVALID_CREDENTIALS");
			}
		}catch(DisabledException e) {
			throw new Exception("USER_DISABLED",e);
		}catch(BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS",e);
		}
	}
	
	public static String getSecurePassword(String password, String salt) throws InvalidKeyException{
		String generatedPassword = null;
		try {
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(salt.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
			sha256_HMAC.init(secret_key);
			
			generatedPassword = Hex.encodeHexString(sha256_HMAC.doFinal(password.getBytes(StandardCharsets.UTF_8)));
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
	
	@GetMapping("/validate-token")
	public ResponseEntity<?> validateTokenUsername(){
		return this.userDetailsService.validateTokenUsername();
	}
}

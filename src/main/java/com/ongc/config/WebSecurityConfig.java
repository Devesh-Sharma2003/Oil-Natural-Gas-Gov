package com.ongc.config;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	    @Autowired
	    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	    
	    @Autowired
	    private JwtRequestFilter filter;
	    
	    @Autowired
	    private UserDetailsService jwtUserDetailsService;
	    
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	    	configure AuthenticationManager so that it knows from where to lead
//	    	user for matching credentials
//	    	use BCryptPasswwordEncoder
	    	auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	    	return new PasswordEncoder() {
				
				@Override
				public boolean matches(CharSequence rawPassword, String encodedPassword) {
					return getMd5(rawPassword.toString()).equals(encodedPassword);
				}
				
				@Override
				public String encode(CharSequence rawPassword) {
					return getMd5(rawPassword.toString());
				}
			};
	    }
	    
	    public static String getMd5(String input) {
	    	try {
//	    		Static getInstance method is called with hashing SHA
	    		MessageDigest md = MessageDigest.getInstance("MD5");
	    		
//	    		digest() method called
//	    		to calculate message digest of an input
//	    		and return array of byte
	    		byte[] messageDigest = md.digest(input.getBytes());
	    		
//	    		Convert byte array into signum representation
	    		BigInteger no = new BigInteger(1,messageDigest);
	    		
//	    		Convert message digest into hex value
	    		String hashtext = no.toString(16);
	    		
	    		while(hashtext.length()<32) {
	    			hashtext = "0"+hashtext;
	    		}
	    		return hashtext;
	    	}catch(Exception e) {
	    		return null;
	    	}
	    }

	    @Bean
	    public void securityFilterChain(HttpSecurity http) throws Exception {
	    	
//	    	we don't need csrf for this example
	        http.csrf().disable()
//	        don't authenticate this particular request
	                .authorizeRequests()
//	                antMatcher("/**")
	                .antMatchers("/authenticate")
	                .permitAll()
//	                all other requests need to be authenticated
	                .anyRequest()
	                .authenticated()
//	                make sure we use stateless session; session won't be used to store user's state
	                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	        
//	        Add a filter to validate the tokens with every request
	        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	        http.cors();
	    }
}

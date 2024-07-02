package com.ongc.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ongc.service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	

	@Autowired
    private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwtToken = null;
		
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
            try {

                username = this.jwtTokenUtil.getUsernameFromToken(jwtToken);

            } catch (IllegalArgumentException e) {
//                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            }
        } else {
            logger.info("Invalid Header Value !! ");
        }


        //
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            //fetch user detail from username
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtTokenUtil.validateToken(jwtToken, userDetails);
            if (validateToken) {

                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } else {
                logger.info("Validation fails !!");
            }
        }

        chain.doFilter(request, response);
	}
}
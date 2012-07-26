package com.megalogika.sv.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class ZebraFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (null != request && 
				("sveikasvaikas.zebra.lt".equals(request.getServerName()) ||
				"zebra.sveikasvaikas.lt".equals(request.getServerName()) ||  
				"dropzone".equals(request.getServerName())) 
			) 
		{
			request.setAttribute("ZEBRA", Boolean.TRUE);
		}
		
		filterChain.doFilter(request, response);

	}

}

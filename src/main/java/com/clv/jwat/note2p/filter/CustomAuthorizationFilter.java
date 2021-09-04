package com.clv.jwat.note2p.filter;

import com.clv.jwat.note2p.jwt.JWTProvider;
import com.clv.jwat.note2p.api.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring("Bearer ".length());
                String subject = JWTProvider.verifyToken(token);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(subject, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } catch (Exception exception) {
                log.error("Error request: {}", exception.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType(APPLICATION_JSON_VALUE);
                ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.FORBIDDEN.value());
                new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}

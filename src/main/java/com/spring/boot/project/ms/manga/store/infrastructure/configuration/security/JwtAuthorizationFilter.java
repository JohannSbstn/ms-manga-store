package com.spring.boot.project.ms.manga.store.infrastructure.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.project.ms.manga.store.infrastructure.exception.TokenExpiredException;
import com.spring.boot.project.ms.manga.store.infrastructure.exception.TokenInvalidException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            try {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        tokenService.getAuthentication(token);
                if (usernamePasswordAuthenticationToken != null) {
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    writeErrorResponse(response, "Expired session", HttpStatus.UNAUTHORIZED);
                }
            } catch (TokenInvalidException ex) {
                writeErrorResponse(response, ex.getMessage(), HttpStatus.BAD_REQUEST);
            } catch (TokenExpiredException ex) {
                writeErrorResponse(response, ex.getMessage(), HttpStatus.UNAUTHORIZED);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void writeErrorResponse(HttpServletResponse response, String message, HttpStatus status)
            throws IOException {
        response.setHeader("error:", message);
        response.setStatus(status.value());
        Map<String, String> error = new HashMap<>();
        error.put("title:", message);
        error.put("status:", String.valueOf(status.value()));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
}

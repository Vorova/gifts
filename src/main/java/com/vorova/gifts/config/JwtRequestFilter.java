package com.vorova.gifts.config;

import com.vorova.gifts.service.util.JwtTokenUtils;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;

    @Autowired
    public JwtRequestFilter(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filter) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        Long userId = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtTokenUtils.getUsername(jwt);
                userId = jwtTokenUtils.getId(jwt);
            } catch (SignatureException e) {
                log.info(
                    "Jwt: попытка подключения с неверной сигнатурой. Ip запроса : " + request.getRemoteAddr());
            } catch (MalformedJwtException e) {
                log.info(
                    "Jwt: попытка подключения с ломаным JWT. Ip запроса : " + request.getRemoteAddr());
            } catch (Exception e) {
                // ignore
            }
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        username,
                        userId,
                        jwtTokenUtils.getRoles(jwt)
                                .stream()
                                .map(SimpleGrantedAuthority::new)
                                .toList()
                );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filter.doFilter(request, response);
    }
}

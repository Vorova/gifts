package com.vorova.gifts.controller;

import com.vorova.gifts.model.dto.AppErrorDto;
import com.vorova.gifts.model.dto.JwtResponse;
import com.vorova.gifts.model.dto.LoginDto;
import com.vorova.gifts.service.imp.UserServiceImpl;
import com.vorova.gifts.service.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private final UserServiceImpl userService;
    private final JwtTokenUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserServiceImpl userService,
                          JwtTokenUtils jwtUtils,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()
            ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AppErrorDto (
                    HttpStatus.UNAUTHORIZED.value(),
                    "Плохие данные"
            ));
        }
        UserDetails userDetails = userService.loadUserByUsername(loginDto.getUsername());
        JwtResponse jwtResponse = new JwtResponse(jwtUtils.generateToken(userDetails));
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
package com.vorova.gifts.controller;

import com.vorova.gifts.model.dto.AppErrorDto;
import com.vorova.gifts.model.dto.JwtResponse;
import com.vorova.gifts.model.dto.LoginDto;
import com.vorova.gifts.service.impl.UserServiceImpl;
import com.vorova.gifts.service.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth/")
public class AuthController extends AbstractController {

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginDto.getUsername(), loginDto.getPassword()
        ));
        UserDetails userDetails = userService.loadUserByUsername(loginDto.getUsername());
        return new ResponseEntity<>(
                new JwtResponse(jwtUtils.generateToken(userDetails)),
                HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<AppErrorDto> handlerException(BadCredentialsException e){
        List<String> errors = new ArrayList<>();
        errors.add("Некорректные данные для авторизации");
        errors.add(e.getMessage());
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                 new AppErrorDto (
                        HttpStatus.UNAUTHORIZED.value(),
                        errors
        ));
    }
}
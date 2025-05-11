package com.demo.jwt.controller;

import com.demo.jwt.model.qdo.AccountQdo;
import com.demo.jwt.model.rdo.GenericResponse;
import com.demo.jwt.service.interfaces.AuthService;
import com.demo.jwt.utilities.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<String>> login(@RequestParam String username, @RequestParam String password){

        String token = authService.login(username,password);

        return ResponseEntity.ok(new GenericResponse<>(HttpStatus.OK.value(), Constants.SUCCESS,token));

    }

    @PostMapping("/sign-up")
    public ResponseEntity<GenericResponse<String>> signUp(@RequestBody AccountQdo accountQdo){

        authService.signUp(accountQdo);

        return ResponseEntity.ok(new GenericResponse<>(HttpStatus.OK.value(),Constants.SUCCESS,Constants.CREATE_ACCOUNT_SUCCESS));
    }

    @GetMapping("/hello")
    public ResponseEntity<GenericResponse<String>> getHello(){
        return ResponseEntity.ok(new GenericResponse<String>(HttpStatus.OK.value(),Constants.SUCCESS, "Hi"));
    }

    @GetMapping("/csrf-token")
    public ResponseEntity<GenericResponse<CsrfToken>> getToken(HttpServletRequest request){

        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");

        return ResponseEntity.ok(new GenericResponse<>(HttpStatus.OK.value(),Constants.SUCCESS,csrfToken));
    }
}

package com.employee.back_gestionTempsDeTravail.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.employee.back_gestionTempsDeTravail.token.TokenService;

@RestController
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class TokenController {
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/generate-token")
    public String generateToken() {
        return tokenService.createToken();
    }

    @GetMapping("verifierToken/{token}")
    public ResponseEntity<String> afficherPageHumeur(@PathVariable String token) {
    if (tokenService.isTokenValid(token)) {
        tokenService.extractTokenGenerationDate(token);
        return ResponseEntity.ok("Token valide");
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalide");
    }
}
 
}



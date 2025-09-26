package com.employee.back_gestionTempsDeTravail.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.back_gestionTempsDeTravail.entity.Humeur;
import com.employee.back_gestionTempsDeTravail.repository.HumeurRepository;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TokenService {
    private final String secretKey = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @Autowired
    private HumeurRepository humeurRepository;

    public String createToken() {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 86400000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    public Date extractTokenGenerationDate(String token) {
        return extractClaim(token, Claims::getIssuedAt);
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

        public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public boolean isTokenValid(String token){

        Optional<Humeur> optionalHumeur = humeurRepository.findById(token);
        if (optionalHumeur.isPresent()) {
            return false;
        }
        return (!isTokenExpired(token));
    }
}

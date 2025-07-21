package com.filmNest.example.filmNest.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "secret345";

    private final long EXPIRATION_TIME = 1000 * 60 * 80 * 10;

    public String generateToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public String extractUsername(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody().getSubject();

}
    public boolean validateToken(String token,String username){
        String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token){
        Date expirationDate = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody().getExpiration();
        return expirationDate.before(new Date());
    }
}

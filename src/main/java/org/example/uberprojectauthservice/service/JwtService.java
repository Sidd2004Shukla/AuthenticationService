package org.example.uberprojectauthservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements CommandLineRunner {
    @Value("${jwt.expiry}")
    private int expiry;
    @Value("${jwt.secret}")
    private String secret;
    private String createToken(Map<String ,Object> payload, String email){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000L);
        return Jwts.builder()
                .addClaims(payload)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .setSubject(email)
                .signWith(getKey())
                .compact();
    }
    private Claims extractAllPlayloads(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaim(String token , Function<Claims, T> claimsResolver){
        final Claims claims = extractAllPlayloads(token);
        return claimsResolver.apply(claims);
    }
    private Date getExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    /**
     * @param token Jwt token
     * @return true it expiration date is before current date
     */
    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
    private String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }
    private Key getKey(){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return key;
    }
    private boolean validateToken(String token, String email){
        final String userEmailFetchedFromToken = extractEmail(token);
        return userEmailFetchedFromToken.equals(email) && !isTokenExpired(token);
    }
    private Object extractPayload(String token , String payloadKey){
        Claims claims = extractAllPlayloads(token);
        return (Object) claims.get(payloadKey);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String ,Object> payload = new HashMap<>();
        payload.put("email","siddharth@gmail.com");
        payload.put("phone","8960139118");
        String result=createToken(payload,"siddharth");
        System.out.println("the token is generated and is "+result);
        System.out.println(extractPayload(result,"email").toString());
    }
}

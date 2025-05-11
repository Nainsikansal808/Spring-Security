package com.demo.jwt.utilities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    static String secretKey = "ZmYwYzJlYjFjMjI3ZmQwYzJlMjZjZGI3N2M0MjQwM2M1ZDU2ZGJlYjFhNzY0Yzc5MmM=";

    public static String generateToken(String userName){

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*4))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
    public static boolean validateToken(String token,String userName){
        return userName.equals(extractUserName(token)) && !isTokenExpired(token);
    }

    public static String extractUserName(String token){
       return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public static boolean isTokenExpired(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}

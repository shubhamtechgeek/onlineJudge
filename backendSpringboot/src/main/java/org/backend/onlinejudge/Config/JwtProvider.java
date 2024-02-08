package org.backend.onlinejudge.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider{

//    private final JwtConstant jwtConstant;
//    public JwtProvider(JwtConstant jwtConstant){
//        this.jwtConstant = jwtConstant;
//    }

    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    public String generateToken(Authentication auth){

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+846000000))
                .claim("email",auth.getName())
                .signWith(key).compact();
    }

    public String getEmailFromToken(String jwt) {
        jwt=jwt.substring(7);

        Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email=String.valueOf(claims.get("email"));

        return email;
    }
}

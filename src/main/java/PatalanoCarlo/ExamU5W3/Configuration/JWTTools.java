package PatalanoCarlo.ExamU5W3.Configuration;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Date;
@Component
public class JWTTools {

    @Value("${jwt_secret}")
    private String secret;

    public String createToken(Long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 1000 * 60 * 60 * 24 * 7);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("role", "ORGANIZER")
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }
}
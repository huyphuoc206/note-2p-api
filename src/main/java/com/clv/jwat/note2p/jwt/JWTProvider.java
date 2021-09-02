package com.clv.jwat.note2p.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.clv.jwat.note2p.entity.AppUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class JWTProvider {

    private static final String SECRET_KEY = "JWAT-Phuoc-Phat";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public static String getAccessToken(AppUser appUser) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24 * 3);
//        calendar.add(Calendar.SECOND, 10);
        return JWT.create()
                .withSubject(appUser.getId().toString())
                .withExpiresAt(calendar.getTime())
                .withIssuedAt(new Date())
                .withClaim("fullname", appUser.getFullname())
                .sign(algorithm);
    }

    public static String getRefreshToken(AppUser appUser) {
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.SECOND, 22);
        calendar.add(Calendar.HOUR, 24 * 7);
        return JWT.create()
                .withSubject(appUser.getId().toString())
                .withExpiresAt(calendar.getTime())
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public static String verifyToken(String token) throws Exception {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String userId = decodedJWT.getSubject();
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            return userId;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

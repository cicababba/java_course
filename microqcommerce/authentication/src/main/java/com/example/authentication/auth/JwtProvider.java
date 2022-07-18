package com.example.authentication.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.joda.time.DateTime;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtProvider {
    private static final String issuer = "qcommerce";
    public static String secret;
    public static String prefix;
    public static String header;

    @Autowired
    public JwtProvider(Environment env) {
        JwtProvider.secret = env.getProperty("security.secret");
        JwtProvider.prefix = env.getProperty("security.prefix");
        JwtProvider.header = env.getProperty("security.param");
        if(JwtProvider.secret == null || JwtProvider.prefix == null || JwtProvider.header == null) {
            throw new BeanInitializationException("JWT properties not set");
        }
    }

    public static String createJwt(String subject, Map<String, Object> claims) {
        System.out.println("JwtProvider createJwt");
        JWTCreator.Builder builder = JWT.create().withSubject(subject).withIssuer(issuer);
        DateTime now = DateTime.now();
        builder.withIssuedAt(now.toDate()).withExpiresAt(now.plusDays(1).toDate());

        for(Map.Entry<String, Object> entry : claims.entrySet()) {
            builder.withClaim(entry.getKey(), entry.getValue().toString());
        }
        return builder.sign(Algorithm.HMAC256(JwtProvider.secret));
    }

    public static DecodedJWT verifyJwt(String jwt) {
        return JWT.require(Algorithm.HMAC256(JwtProvider.secret)).build().verify(jwt);
    }
}

package com.amadeus.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

    @Value("${JWT.secret-key}")
    String secretKey;

    @Value("${JWT.issuer}")
    String issuer;

    @Value("${JWT.audience}")
    String audience;

    public Optional<String> createToken(Long id){
        String token;
        Long exDate = 1000L*60*30;
        try {
           token = JWT.create()
                    .withAudience(audience)
                    .withClaim("id",id)
                    .withClaim("other", "auths-microservice")
                    .withClaim("isOneKey", true)
                    .withIssuer(issuer) //sahbi
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + exDate))
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Boolean validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
           DecodedJWT decodedJWT = verifier.verify(token);
           if (decodedJWT == null)
               return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Optional<Long> getByIdFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null)
                return Optional.empty();
            return Optional.of(decodedJWT.getClaim("id").asLong());
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}

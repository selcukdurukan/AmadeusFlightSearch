package com.amadeus.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenManager {

    @Value("${JWT.secret-key}")
    String secretKey;

    @Value("${JWT.issuer}")
    String issuer;

    @Value("${JWT.audience}")
    String audience;

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



}

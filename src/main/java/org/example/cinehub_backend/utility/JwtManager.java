package org.example.cinehub_backend.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class JwtManager {

    private String secretKey = "XYMEZ2IlOZqScWt";
    private String issuer = "CineHubProject";

    private final Long ExDate = 1000L * 60 * 60;

    public String createToken(Long authId, String key) {
        Date createdDate = new Date(System.currentTimeMillis()); //oluşturulma tarihi
        Date expirationDate = new Date(System.currentTimeMillis() + ExDate); //token geçerlilik zamanı
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withAudience()
                .withIssuer(issuer)
                .withIssuedAt(createdDate)
                .withExpiresAt(expirationDate)
                .withClaim("authId", authId)
                .withClaim("key", key)
                .sign(algorithm);
    }

    //admin kullanıcısı için token oluşturuyor.
    public String createAdminToken(Long authId) {
        return createToken(authId, "ADMIN");
    }

    //user kullanıcısı için token oluşturuyor.
    public String createUserToken(Long authId) {
        return createToken(authId, "USER");
    }

    public Optional<Long> validateToken(String token, String expectedKey) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("key", expectedKey)
                    .withIssuer(issuer)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            if (Objects.isNull(jwt)){
                return Optional.empty();
            }
            return Optional.of(jwt.getClaim("authId").asLong());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}

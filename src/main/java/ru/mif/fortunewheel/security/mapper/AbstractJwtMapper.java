package ru.mif.fortunewheel.security.mapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mif.fortunewheel.domain.PersistentObject;
import ru.mif.fortunewheel.security.JWTMapper;
import ru.mif.fortunewheel.utils.KeyCreators;

import java.security.Key;
import java.util.Optional;

public abstract class AbstractJwtMapper<ENTITY extends PersistentObject> implements JWTMapper<ENTITY> {

    protected final String ROLE_KEY = "role";

    protected final Logger logger = LoggerFactory.getLogger(ApiClientJWTMapper.class);

    protected final Key key;
    protected final long expiration;
    protected final String issuer;

    protected AbstractJwtMapper(String encodedKey, long expiration, String issuer) {
        this.key = KeyCreators.build(encodedKey);
        this.expiration = expiration;
        this.issuer = issuer;
    }

    @Override
    public boolean isValid(String token) {
        return parseJwt(token).isPresent();
    }

    protected Optional<Jwt<?,?>> parseJwt(String token) {
        var parser = Jwts.parserBuilder()
                .setSigningKey(key).requireIssuer(issuer).build();

        Jwt<?,?> jwt = null;
        try {
            jwt = parser.parse(token);
        } catch (ExpiredJwtException e) {
            logger.warn("Token {} was expired.", token);
//            e.printStackTrace();
        } catch (MalformedJwtException e) {
            logger.warn("Token {} is MalformedJwt token.", token);
//            e.printStackTrace();
        } catch (SignatureException e) {
            logger.warn("Token {} was signed with another key Signature", token);
//            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            logger.warn("Token {} was IllegalArgument in {}", token);
//            e.printStackTrace();
        } catch (Exception e) {
            logger.warn("Uncaught exception in token {} parsing process", token);
//            e.printStackTrace();
        }
        return Optional.ofNullable(jwt);
    }
}

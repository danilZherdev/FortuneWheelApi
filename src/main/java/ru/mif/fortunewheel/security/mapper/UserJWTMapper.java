package ru.mif.fortunewheel.security.mapper;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.dto.data.UserData;
import ru.mif.fortunewheel.enums.UserRole;
import ru.mif.fortunewheel.repository.UserRepository;
import ru.mif.fortunewheel.security.AuthenticationException;
import ru.mif.fortunewheel.security.Identified;
import ru.mif.fortunewheel.utils.DateUtils;

import java.util.Date;

@Service
public class UserJWTMapper extends AbstractJwtMapper<User> {

    private final static String EMAIL_KEY = "email";
    private final static String HASH_KEY = "hash";

    private final UserRepository repository;

    protected UserJWTMapper(@Value("${jwt.secret.key:default_key}") String encodedKey,
                            @Value("${jwt.token.expiration:0}") long expiration,
                            @Value("${jwt.token.issuer:ANONYMOUS}") String issuer,
                            UserRepository repository) {
        super(encodedKey, expiration, issuer);
        this.repository = repository;
    }

    @Override
    public TokenData<User> create(User user) {
        if (user == null)
            throw new AuthenticationException("entity was empty.");

        var expiredAt = new Date(System.currentTimeMillis() + expiration);
        String token = Jwts.builder()
                .setExpiration(expiredAt)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setHeaderParam(EMAIL_KEY, user.getEmail())
                .setHeaderParam(HASH_KEY, user.getHash())
                .setHeaderParam(ROLE_KEY, UserRole.API_CLIENT_ROLE)
                .signWith(key)
                .compact();

        return new TokenData<>(user, token, DateUtils.fromDate(expiredAt), new UserData(user));
    }

    @Override
    public Identified parse(String token) {
        var jwtOpt = parseJwt(token);
        if (jwtOpt.isEmpty())
            return null;
        var email = jwtOpt.get().getHeader().get(EMAIL_KEY);
        if (email == null) {
            logger.warn("Email in token {} not found", token);
            return null;
        }
        var hash = jwtOpt.get().getHeader().get(HASH_KEY);
        if (hash == null) {
            logger.warn("Hash in token {} not found", token);
            return null;
        }
        var user = repository.findByEmailAndHash(email.toString(), hash.toString());
        if (user.isEmpty()) {
            logger.warn("User for token {} not found", token);
            return null;
        }

        return new Identified(user.get());
    }
}

package ru.mif.fortunewheel.security.mapper;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.domain.APIClient;
import ru.mif.fortunewheel.dto.data.ApiClientData;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.enums.UserRole;
import ru.mif.fortunewheel.repository.ApiClientRepository;
import ru.mif.fortunewheel.security.AuthenticationException;
import ru.mif.fortunewheel.security.Identified;
import ru.mif.fortunewheel.utils.DateUtils;

import java.util.Date;

@Service
public class ApiClientJWTMapper extends AbstractJwtMapper<APIClient> {

    private final static String DOMAIN_KEY = "domain";
    private final static String AGENT_KEY = "agent";

    private final ApiClientRepository repository;

    public ApiClientJWTMapper(@Value("${jwt.secret.api-client.key:default_key}") String encodedKey,
                              @Value("${jwt.token.api-client.expiration:0}") long expiration,
                              @Value("${jwt.token.api-client.issuer:ANONYMOUS}") String issuer,
                              ApiClientRepository repository) {
        super(encodedKey, expiration, issuer);
        this.repository = repository;
    }

    @Override
    public TokenData<APIClient> create(APIClient apiClient) {
        if (apiClient == null)
            throw new AuthenticationException("entity was empty.");

        var expiredAt = new Date(System.currentTimeMillis() + expiration);
        String token = Jwts.builder()
                .setExpiration(expiredAt)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setHeaderParam(DOMAIN_KEY, apiClient.getDomain())
                .setHeaderParam(AGENT_KEY, apiClient.getAgent())
                .setHeaderParam(ROLE_KEY, UserRole.API_CLIENT_ROLE)
                .signWith(key)
                .compact();

        return new TokenData<>(apiClient, token, DateUtils.fromDate(expiredAt), new ApiClientData(apiClient));
    }

    @Override
    public Identified parse(String token) {
        var jwtOpt = parseJwt(token);
        if (jwtOpt.isEmpty())
            throw new AuthenticationException("Authentication failed.");
        var domain = jwtOpt.get().getHeader().get(DOMAIN_KEY).toString();
        if (domain == null) {
            logger.warn("Domain in token {} not found", token);
            throw new AuthenticationException("Authentication failed.");
        }
        var agent = jwtOpt.get().getHeader().get(AGENT_KEY).toString();
        if (agent == null) {
            logger.warn("Agent in token {} not found", token);
            throw new AuthenticationException("Authentication failed.");
        }
        var apiClient = repository.findTopByDomainAndAgent(domain, agent);
        if (apiClient.isEmpty()) {
            logger.warn("ApiClient for token {} not found", token);
            throw new AuthenticationException("Authentication failed.");
        }

        return new Identified(apiClient.get());
    }
}

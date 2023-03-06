package ru.mif.fortunewheel.security;

import ru.mif.fortunewheel.domain.PersistentObject;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.data.TokenData;

public interface JWTMapper<ENTITY extends PersistentObject> {
    /**
     * Creates new signed token by data from {@link ENTITY} object.
     * @param entity object with type {@link ENTITY} from persistent storage.
     * @return object with type {@link Data<ENTITY>} that represents token and token entry.
     */
    TokenData<ENTITY> create(ENTITY entity);

    /**
     * Check is token valid. Check is token has valid structure and not expire.
     * @param token {@link String} result JSON Web Token.
     * @return {@literal true} if token is valid JSON Web Token.
     */
    boolean isValid(String token);

    /**
     * Extract {@link Data<ENTITY>} from {@link String} JWT token.
     * @param token {@link String} result JSON Web Token.
     * @return extracted JWT data object with {@link Data<ENTITY>} type.
     */
    Data<ENTITY> parse(String token);
}

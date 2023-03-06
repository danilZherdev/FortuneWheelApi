package ru.mif.fortunewheel.utils;

import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class KeyCreators {

    private static final Logger logger = LoggerFactory.getLogger(KeyCreators.class);

    /**
     * Build HMAC512 secret key with type {@link SecretKey} for encoded {@link String} key.
     * @param encodedKey encoded {@link String} HMAC512 key.
     * @return {@link SecretKey} for encoded {@link String} key.
     */
    public static SecretKey build(String encodedKey) {
        SecretKey key = null;
        try {
            byte[] entry = encodedKey.getBytes(StandardCharsets.UTF_8);
            key = Keys.hmacShaKeyFor(entry);
        } catch (NullPointerException e) {
            logger.error("NullPointerException when get encodedKey = {}", encodedKey);
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException when getBytes from encodedKey = {}", encodedKey);
            e.printStackTrace();
        } catch (WeakKeyException e) {
            logger.error("Key {} is weak. Need put stronger key", encodedKey);
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            logger.error("InvalidKeyException when call Keys.hmacShaKeyFor() from encodedKey = {}", encodedKey);
            e.printStackTrace();
        }
        return key;
    }
}

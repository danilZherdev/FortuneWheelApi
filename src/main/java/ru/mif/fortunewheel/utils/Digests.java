package ru.mif.fortunewheel.utils;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Digests {

    public static String generateRandomHash() {
        SecureRandom random = new SecureRandom();
        var rnd1 = new BigInteger(128, random).toString(32);
        var rnd2 = new BigInteger(128, random).toString(32);
        var timeBasedPart = Arrays.toString(
                DigestUtils.md5Digest(
                        (LocalDateTime.now().getNano() + rnd1 + rnd2).getBytes(StandardCharsets.UTF_8)
                ));
        var rnd1Part = Arrays.toString(DigestUtils.md5Digest(rnd1.getBytes(StandardCharsets.UTF_8)));
        var rnd2Part = Arrays.toString(DigestUtils.md5Digest(rnd2.getBytes(StandardCharsets.UTF_8)));

        return String.format("Tz%s-%s-%s", timeBasedPart, rnd1Part, rnd2Part);
    }
}

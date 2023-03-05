package ru.mif.fortunewheel.dto.models;

import ru.mif.fortunewheel.domain.APIClient;
import ru.mif.fortunewheel.dto.Model;
import ru.mif.fortunewheel.utils.Patterns;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class APIClientModel implements Model<APIClient> {

    @Pattern(regexp = Patterns.URL_PATTERN, message = "Указанный домен не является валидным доменом.")
    private String domain;
    private String agent;
    @Min(value = 128, message = "Неверный secret")
    @Max(value = 1024, message = "Неверный secret")
    private String secret;

    public APIClientModel() {
    }

    public APIClientModel(String domain, String agent, String secret) {
        this.domain = domain;
        this.agent = agent;
        this.secret = secret;
    }

    public String getDomain() {
        return domain;
    }

    public String getAgent() {
        return agent;
    }

    public String getSecret() {
        return secret;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public APIClient toEntity() {
        return new APIClient(this.getDomain(), this.getAgent(), this.getSecret());
    }
}

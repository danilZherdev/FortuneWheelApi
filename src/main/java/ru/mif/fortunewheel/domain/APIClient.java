package ru.mif.fortunewheel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "api_clients")
public class APIClient extends PersistentObject {

    @Column(name = "domain", nullable = false)
    private String domain;
    @Column(name = "agent", nullable = false)
    private String agent;
    @Column(name = "secret", nullable = false)
    private String secret;

    public APIClient() {}

    public APIClient(String domain, String agent, String secret) {
        this.domain = domain;
        this.agent = agent;
        this.secret = secret;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}

package ru.mif.fortunewheel.domain;

import ru.mif.fortunewheel.enums.SpinStatusType;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "spins")
public class Spin extends PersistentObject {

    @Column(name = "hash", nullable = false)
    private String hash;

    @Column(name = "status", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private SpinStatusType status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Spin() {}

    public Spin(String hash, SpinStatusType status, User user) {
        this.hash = hash;
        this.status = status;
        this.user = user;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public SpinStatusType getStatus() {
        return status;
    }

    public void setStatus(SpinStatusType status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

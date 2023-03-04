package ru.mif.fortunewheel.domain;

import ru.mif.fortunewheel.enums.SpinStatusType;

import javax.persistence.*;

@Entity
@Table(name = "spins")
public class Spin extends PersistentObject {

    @Column(name = "used", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean used;

    @Column(name = "status", nullable = false)
    private SpinStatusType status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Spin() {}

    public Spin(boolean used, SpinStatusType status, User user) {
        this.used = used;
        this.status = status;
        this.user = user;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
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

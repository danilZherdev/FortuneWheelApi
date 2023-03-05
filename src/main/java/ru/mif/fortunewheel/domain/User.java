package ru.mif.fortunewheel.domain;

import ru.mif.fortunewheel.enums.UserRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends PersistentObject {

    @Column(name = "email", unique = true, nullable = false, updatable = false)
    private String email;
    @Column(name="hash", columnDefinition = "VARCHAR(512) UNIQUE NOT NULL CHECK (length(hash) = 512)")
    private String hash;
    @Column(name = "role", nullable = false)
    private UserRole role;

    public User() {}

    public User(String email, String hash, UserRole role) {
        this.email = email;
        this.hash = hash;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}

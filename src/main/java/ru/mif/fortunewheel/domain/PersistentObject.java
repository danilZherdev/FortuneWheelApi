package ru.mif.fortunewheel.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class PersistentObject {

    /**
     * Identifier of entity -> primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    private long id;
    /**
     * Date when entity was created
     */
    @Column(name = "created_at", nullable = false, columnDefinition = "date DEFAULT CURRENT_DATE")
    private ZonedDateTime createdAt;
    /**
     * Last date when entity was updated.
     */
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
    /**
     * Date when entity was deleted.
     */
    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ZonedDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(ZonedDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    /**
     * Check if entity is deleted.
     * @return TRUE - if entity is deleted.
     */
    public boolean isDeleted() {
        return deletedAt != null && deletedAt.isBefore(ZonedDateTime.now());
    }
}

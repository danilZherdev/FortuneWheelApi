package ru.mif.fortunewheel.domain;

import ru.mif.fortunewheel.enums.PrizeType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "prizes")
public class Prize extends PersistentObject{

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "uri", nullable = false)
    private String uri;

    @Column(name = "type", nullable = false)
    private PrizeType type;

    public Prize() {}

    public Prize(String title, String description, String uri, PrizeType type) {
        this.title = title;
        this.description = description;
        this.uri = uri;
        this.type = type;
    }

    public Prize(long id, String title, String description, String uri, PrizeType type) {
        this.setId(id);
        this.title = title;
        this.description = description;
        this.uri = uri;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public PrizeType getType() {
        return type;
    }

    public void setType(PrizeType type) {
        this.type = type;
    }
}

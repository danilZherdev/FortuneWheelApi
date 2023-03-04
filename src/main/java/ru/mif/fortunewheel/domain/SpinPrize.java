package ru.mif.fortunewheel.domain;

import javax.persistence.*;

@Entity
@Table(name = "spin_prizes")
public class SpinPrize extends PersistentObject {

    @Column(name = "given", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean given;

    @ManyToOne
    @JoinColumn(name = "spin_id")
    private Spin spin;

    @ManyToOne
    @JoinColumn(name = "prize_id")
    private Prize prize;

    public SpinPrize() {}

    public SpinPrize(boolean given, Spin spin, Prize prize) {
        this.given = given;
        this.spin = spin;
        this.prize = prize;
    }

    public boolean isGiven() {
        return given;
    }

    public void setGiven(boolean given) {
        this.given = given;
    }

    public Spin getSpin() {
        return spin;
    }

    public void setSpin(Spin spin) {
        this.spin = spin;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}

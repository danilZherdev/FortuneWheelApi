package ru.mif.fortunewheel.dto.data;

import ru.mif.fortunewheel.domain.SpinPrize;
import ru.mif.fortunewheel.dto.Data;

import java.time.ZonedDateTime;

public class SpinPrizeData implements Data<SpinPrizeData, SpinPrize> {
    private boolean given;
    private SpinData spin;
    private PrizeData prize;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public SpinPrizeData(boolean given, SpinData spin, PrizeData prize, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.given = given;
        this.spin = spin;
        this.prize = prize;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean isGiven() {
        return given;
    }

    public void setGiven(boolean given) {
        this.given = given;
    }

    public SpinData getSpin() {
        return spin;
    }

    public void setSpin(SpinData spin) {
        this.spin = spin;
    }

    public PrizeData getPrize() {
        return prize;
    }

    public void setPrize(PrizeData prize) {
        this.prize = prize;
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

    @Override
    public SpinPrizeData fromEntity(SpinPrize spinPrize) {
        return new SpinPrizeData(
                spinPrize.isGiven(),
                new SpinData().fromEntity(spinPrize.getSpin()),
                new PrizeData().fromEntity(spinPrize.getPrize()),
                spinPrize.getCreatedAt(),
                spinPrize.getUpdatedAt()
        );
    }
}

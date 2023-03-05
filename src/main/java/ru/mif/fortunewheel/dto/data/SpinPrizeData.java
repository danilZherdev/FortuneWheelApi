package ru.mif.fortunewheel.dto.data;

import ru.mif.fortunewheel.domain.SpinPrize;
import ru.mif.fortunewheel.dto.Data;

import java.time.ZonedDateTime;

public final class SpinPrizeData extends Data<SpinPrize> {
    private final boolean given;
    private final SpinData spin;
    private final PrizeData prize;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;

    public SpinPrizeData(SpinPrize spinPrize) {
        super(spinPrize);
        this.given = spinPrize.isGiven();
        this.spin = new SpinData(spinPrize.getSpin());
        this.prize = new PrizeData(spinPrize.getPrize());
        this.createdAt = spinPrize.getCreatedAt();
        this.updatedAt = spinPrize.getUpdatedAt();
    }

    public boolean isGiven() {
        return given;
    }

    public SpinData getSpin() {
        return spin;
    }

    public PrizeData getPrize() {
        return prize;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
}

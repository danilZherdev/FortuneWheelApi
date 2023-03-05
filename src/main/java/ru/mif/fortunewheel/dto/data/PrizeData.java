package ru.mif.fortunewheel.dto.data;

import ru.mif.fortunewheel.domain.Prize;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.enums.PrizeType;

public class PrizeData extends Data<Prize> {

    private final long id;
    private final String title;
    private final String uri;
    private final PrizeType prizeType;
    private final String description;

    public PrizeData(Prize prize) {
        super(prize);
        this.id = prize.getId();
        this.title = prize.getTitle();
        this.uri = prize.getUri();
        this.prizeType = prize.getType();
        this.description = prize.getDescription();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }

    public PrizeType getPrizeType() {
        return prizeType;
    }

    public String getDescription() {
        return description;
    }
}

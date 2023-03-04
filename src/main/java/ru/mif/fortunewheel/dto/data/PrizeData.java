package ru.mif.fortunewheel.dto.data;

import ru.mif.fortunewheel.domain.Prize;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.models.PrizeModel;
import ru.mif.fortunewheel.enums.PrizeType;

public class PrizeData extends PrizeModel implements Data<PrizeData, Prize> {

    public PrizeData() {}

    public PrizeData(long id, String description, String url, PrizeType prizeType) {
        super(id, description, url, prizeType);
    }

    @Override
    public PrizeData fromEntity(Prize prize) {
        return new PrizeData(
                prize.getId(),
                prize.getDescription(),
                prize.getUri(),
                prize.getType()
        );
    }
}

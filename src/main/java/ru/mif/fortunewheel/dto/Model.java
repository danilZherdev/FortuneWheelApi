package ru.mif.fortunewheel.dto;

import ru.mif.fortunewheel.domain.PersistentObject;

public interface Model<MODEL, ENTITY extends PersistentObject> {
    ENTITY toEntity(MODEL model);
}

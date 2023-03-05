package ru.mif.fortunewheel.dto;

import ru.mif.fortunewheel.domain.PersistentObject;

public interface Model<ENTITY extends PersistentObject> {
    ENTITY toEntity();
}

package ru.mif.fortunewheel.dto;

import ru.mif.fortunewheel.domain.PersistentObject;

public interface Data<DATA, ENTITY extends PersistentObject> {

     DATA fromEntity(ENTITY entity);
}

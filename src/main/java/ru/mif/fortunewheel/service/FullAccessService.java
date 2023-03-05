package ru.mif.fortunewheel.service;

import ru.mif.fortunewheel.domain.PersistentObject;
import ru.mif.fortunewheel.dto.Data;

public interface FullAccessService<ENTITY extends PersistentObject>
        extends ReadWriteService<ENTITY> {

    /**
     * Delete entity by id
     * @param id
     * @return D
     */
    Data<ENTITY> delete(long id);

    /**
     * Delete entity by id
     * @param id
     * @return D
     */
    default Data<ENTITY> hardDelete(long id) {
        throw new UnsupportedOperationException();
    }
}

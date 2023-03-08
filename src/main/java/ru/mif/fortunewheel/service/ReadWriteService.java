package ru.mif.fortunewheel.service;

import ru.mif.fortunewheel.domain.PersistentObject;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.Model;

public interface ReadWriteService<ENTITY extends PersistentObject>
        extends ReadOnlyService<ENTITY> {

    /**
     * Creating entity
     * @param model object with type {@link MODEL}
     * @return Data object with type {@link DATA} that represents {@link ENTITY} data.
     */
    Data<ENTITY> create(Model<ENTITY> model);

    /**
     * Updating entity
     * @param model
     * @return Data
     */
    Data<ENTITY> update(Model<ENTITY> model);

    /**
     * Updating entity by id and model
     * @param id identifier of entity.
     * @return Data result data representation of entity.
     */
    Data<ENTITY> remove(long id);
}

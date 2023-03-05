package ru.mif.fortunewheel.service;

import ru.mif.fortunewheel.domain.PersistentObject;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.Page;

/**
 * This interface describes service with only Read access rights.
 */
public interface ReadOnlyService<ENTITY extends PersistentObject>{

    /**
     * Get entity on id
     * @param id id of {@link ENTITY} object.
     * @return Data object with type {@link Data<ENTITY>} that represents {@link ENTITY} data.
     */
    Data<ENTITY> read(long id);

    /**
     * Get entity by number and size of page
     * @param number of reading page.
     * @param size of reading page.
     * @return Page of sequence in persistent storage.
     */
    Page<Data<ENTITY>> read(int number, int size);
}

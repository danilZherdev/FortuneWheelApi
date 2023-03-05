package ru.mif.fortunewheel.service.access;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.domain.Prize;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.Model;
import ru.mif.fortunewheel.dto.data.PrizeData;
import ru.mif.fortunewheel.repository.PrizeRepository;
import ru.mif.fortunewheel.service.FullAccessService;
import ru.mif.fortunewheel.service.ServiceException;

@Service
public class PrizeService implements FullAccessService<Prize> {

    private final Logger logger = LoggerFactory.getLogger(PrizeService.class);

    private PrizeRepository repository;

    public PrizeService(PrizeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Data<Prize> delete(long id) {
        return repository.findById(id)
                .map(PrizeData::new)
                .orElseThrow(() -> new ServiceException(logger, "Prize with id = %s not found", id));
    }

    @Override
    public Data<Prize> read(long id) {
        return repository.findById(id).map(PrizeData::new)
                .orElseThrow(() -> new ServiceException(logger, "Prize with id = %s not found", id));
    }

    @Override
    public Page<Data<Prize>> read(int number, int size) {
        return repository.findAll(PageRequest.of(number, size))
                .map(PrizeData::new);
    }

    @Override
    public Data<Prize> create(Model<Prize> model) {
        return new PrizeData(repository.save(model.toEntity()));
    }

    @Override
    public Data<Prize> update(Model<Prize> model) {
        return new PrizeData(repository.save(model.toEntity()));
    }

    @Override
    public Data<Prize> update(long id, Model<Prize> model) {
        return repository.findById(id)
                .map(e -> new PrizeData(repository.save(model.toEntity())))
                .orElseThrow(() -> new ServiceException(logger, "Prize with id = %s not found", id));
    }
}

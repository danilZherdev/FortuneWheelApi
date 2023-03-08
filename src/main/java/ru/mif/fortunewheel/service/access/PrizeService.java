package ru.mif.fortunewheel.service.access;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.domain.Prize;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.Model;
import ru.mif.fortunewheel.dto.Page;
import ru.mif.fortunewheel.dto.data.PrizeData;
import ru.mif.fortunewheel.dto.models.PrizeModel;
import ru.mif.fortunewheel.repository.PrizeRepository;
import ru.mif.fortunewheel.service.FullAccessService;
import ru.mif.fortunewheel.service.ServiceException;

import java.util.stream.Collectors;

@Service
public class PrizeService implements FullAccessService<Prize> {

    private final Logger logger = LoggerFactory.getLogger(PrizeService.class);

    private final PrizeRepository repository;

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
    public Data<Prize> readForUserOnly(long id) {
        return read(id);
    }

    @Override
    public Page<Prize> read(int number, int size) {
        var page = repository.findAll(PageRequest.of(number, size));
        var items = page.getContent()
                .stream()
                .map(PrizeData::new)
                .collect(Collectors.toList());
        return new Page(page, items);
    }

    @Override
    public Data<Prize> create(Model<Prize> model) {
        var entity = repository.save(model.toEntity());
        return new PrizeData(entity);
    }

    @Override
    public Data<Prize> update(Model<Prize> model) {
        final PrizeModel m = (PrizeModel) model;
        final Prize p = repository.save(m.toEntity(m.getId()));
        return new PrizeData(p);
    }

    @Override
    public Data<Prize> remove(long id) {
        throw new UnsupportedOperationException("Removing of Prize is not supported.");
    }
}

package ru.mif.fortunewheel.service.access;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.domain.SpinPrize;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.data.SpinPrizeData;
import ru.mif.fortunewheel.enums.SpinStatusType;
import ru.mif.fortunewheel.repository.PrizeRepository;
import ru.mif.fortunewheel.repository.SpinPrizeRepository;
import ru.mif.fortunewheel.repository.SpinRepository;
import ru.mif.fortunewheel.repository.UserRepository;
import ru.mif.fortunewheel.service.ReadOnlyService;
import ru.mif.fortunewheel.service.ServiceException;

import java.util.Random;

@Service
public class SpinPrizeService implements ReadOnlyService<SpinPrize> {
    private final Logger logger = LoggerFactory.getLogger(SpinPrizeService.class);

    private final SpinPrizeRepository repository;
    private final SpinRepository spinRepository;
    private final PrizeRepository prizeRepository;
    private final UserRepository userRepository;
    private final SpinPrizeRepository spinPrizeRepository;

    public SpinPrizeService(SpinPrizeRepository repository,
                            SpinRepository spinRepository,
                            PrizeRepository prizeRepository,
                            UserRepository userRepository,
                            SpinPrizeRepository spinPrizeRepository
    ) {
        this.repository = repository;
        this.spinRepository = spinRepository;
        this.prizeRepository = prizeRepository;
        this.userRepository = userRepository;
        this.spinPrizeRepository = spinPrizeRepository;
    }

    public SpinPrizeData create(long spinId) {
        var spinOpt = spinRepository.findByIdAndStatus(spinId, SpinStatusType.NOT_USED);
        if (spinOpt.isEmpty()) {
            throw new ServiceException(logger, "Spin with id = %s not found.", spinId);
        }
        var spin = spinOpt.get();
        spin.setStatus(SpinStatusType.USED);
        spinRepository.save(spin);
        //todo randomizer
        var prizes = prizeRepository.findAll();
        if (prizes.isEmpty()) {
            throw new ServiceException(logger, "No prizes in this spin with id = %s ", spinId);
        }
        var prize = prizes.get(new Random().nextInt(0, prizes.size()));

        var spinPrize = new SpinPrize(spin, prize);
        spinPrize = repository.save(spinPrize);
        return new SpinPrizeData(spinPrize);
    }

    @Override
    public Data<SpinPrize> read(long id) {
        return repository.findById(id)
                .map(SpinPrizeData::new)
                .orElseThrow(() -> new ServiceException(logger, "Prize with id = %s not found", id));
    }

    @Override
    public Page<Data<SpinPrize>> read(int number, int size) {
        return repository.findAll(PageRequest.of(number, size))
                .map(SpinPrizeData::new);
    }

    public Page<SpinPrizeData> read(String userHash, int number, int size) {
        var user = userRepository.findByHash(userHash)
                .orElseThrow(() -> new ServiceException(logger, "User with userHash = %s not found", userHash));

        return spinPrizeRepository
                .findAllBySpin_User(user, PageRequest.of(number, size))
                .map(SpinPrizeData::new);
    }
}

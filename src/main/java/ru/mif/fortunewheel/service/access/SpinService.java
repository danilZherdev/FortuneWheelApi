package ru.mif.fortunewheel.service.access;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.domain.Spin;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.Data;
import ru.mif.fortunewheel.dto.Page;
import ru.mif.fortunewheel.dto.data.SpinData;
import ru.mif.fortunewheel.dto.data.UserData;
import ru.mif.fortunewheel.enums.SpinStatusType;
import ru.mif.fortunewheel.enums.UserRole;
import ru.mif.fortunewheel.repository.SpinRepository;
import ru.mif.fortunewheel.repository.UserRepository;
import ru.mif.fortunewheel.security.AuthenticatedUser;
import ru.mif.fortunewheel.security.AuthenticationException;
import ru.mif.fortunewheel.security.ForbiddenException;
import ru.mif.fortunewheel.service.ReadOnlyService;
import ru.mif.fortunewheel.service.ServiceException;
import ru.mif.fortunewheel.utils.Digests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpinService implements ReadOnlyService<Spin> {
    private final Logger logger = LoggerFactory.getLogger(SpinService.class);

    private final SpinRepository repository;
    private final UserRepository userRepository;

    private final AuthenticatedUser authenticatedUser;

    public SpinService(SpinRepository repository,
                       UserRepository userRepository,
                       AuthenticatedUser authenticatedUser) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.authenticatedUser = authenticatedUser;
    }

    public int notUsedCount(long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(logger, "User with id = %s not found", userId));
        return repository.countByUserAndStatus(user, SpinStatusType.NOT_USED);
    }

    public List<SpinData> create(String email, String userHash, int count) {
        var user = userRepository.findByEmailAndHashAndRole(email, userHash, UserRole.CUSTOMER).orElseGet(() -> {
            final var newUser = new User(email, userHash, UserRole.CUSTOMER);
            return userRepository.save(newUser);
        });
        List<SpinData> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            var spinHash = Digests.generateRandomHash();
            var spin = new Spin(spinHash, SpinStatusType.NOT_USED, user);
            spin = repository.save(spin);
            data.add(new SpinData(spin));
        }
        return data;
    }

    public List<SpinData> burn(String email, String userHash, int count) {
        var user = userRepository.findByHash(userHash).orElseGet(() -> {
            final var newUser = new User(email, userHash, UserRole.CUSTOMER);
            return userRepository.save(newUser);
        });
        List<SpinData> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            repository.findFirstByUserAndStatus(user, SpinStatusType.NOT_USED).ifPresent(s -> {
                s.setStatus(SpinStatusType.BURNED);
                repository.save(s);
                data.add(new SpinData(s));
            });
        }
        return data;
    }

    @Override
    public Data<Spin> read(long id) {
        return repository.findById(id).map(SpinData::new)
                .orElseThrow(() -> new ServiceException(logger, "Spin with id = %s not found", id));
    }

    @Override
    public Data<Spin> readForUserOnly(long id) {
        //get current user from SecurityContextHolder
        var currentUser = authenticatedUser.get();
        if (currentUser.isEmpty()) {
            throw new AuthenticationException("Authentication failed.");
        }
        //get resource by id
        var spin = repository.findById(id)
                .orElseThrow(() -> new ServiceException(logger, "User with id = %s not found.", id));
        //check if resource is resource of authenticated user
        if (spin.getUser().getId() != currentUser.get().getId()) {
            throw new ForbiddenException("This resource is not your.");
        }
        // give resource
        return new SpinData(spin);
    }

    @Override
    public Page<Spin> read(int number, int size) {
        var page = repository.findAll(PageRequest.of(number, size));
        var items = page.getContent()
                .stream()
                .map(SpinData::new)
                .collect(Collectors.toList());
        return new Page(page, items);
    }

    public Page<Spin> read(String userHash, int number, int size) {
        var user = userRepository.findByHash(userHash)
                .orElseThrow(() -> new ServiceException(logger, "User with userHash = %s not found", userHash));

        var page = repository.findAllByUser(user, PageRequest.of(number, size));
        var items = page.getContent()
                .stream()
                .map(SpinData::new)
                .collect(Collectors.toList());

        return new Page(page, items);
    }


    public SpinData readNotUsed(long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ServiceException(logger, "User with id = %s not found", id));

        return repository.findFirstByUserAndStatus(user, SpinStatusType.NOT_USED)
                .map(SpinData::new)
                .orElseThrow(() -> new ServiceException(logger, "You has not any not used spins in your account"));
    }
}

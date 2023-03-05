package ru.mif.fortunewheel.service.access;

import org.springframework.stereotype.Service;
import ru.mif.fortunewheel.dto.data.TokenData;
import ru.mif.fortunewheel.repository.ApiClientRepository;
import ru.mif.fortunewheel.service.AccountService;

@Service
public class APIClientService implements AccountService {

    private ApiClientRepository repository;

    @Override
    public TokenData authenticate(String username, String password) {
        return null;
    }
}

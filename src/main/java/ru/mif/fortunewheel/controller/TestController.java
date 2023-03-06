package ru.mif.fortunewheel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mif.fortunewheel.domain.APIClient;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.enums.UserRole;
import ru.mif.fortunewheel.repository.ApiClientRepository;
import ru.mif.fortunewheel.repository.UserRepository;
import ru.mif.fortunewheel.security.mapper.ApiClientJWTMapper;
import ru.mif.fortunewheel.security.mapper.UserJWTMapper;

@RestController
@RequestMapping("/test")
public class TestController {

    private static String RND_HASH = "01ms0bRp9yt2cXvolsAxHd36aaGhZJ2xhznqT9edun10k34aFPaPrYL9wQf8Ti" +
                                     "B46HG09xje7pffevu6nVsofE7rs1RKyjpeEjNxncz2ws7cjYeXLjotRoUz0d7ES" +
                                     "9nuryjjMqh4pZ35oGmstGL2KrNk2Ry5yZYbROjcIoQYT4YQkmOYSf6RggoCJ7RN" +
                                     "4K7ATrVdeGgu4ktiYpQO6ayXXxcfUPUWvQezSU1wrgnbzZdOz0VlhSCBnrfSvmok" +
                                     "JrfKED0nDP8Ad9VyinfVe9dqDBiFXu3uaup7UlGMADOg1NV69C2pBYLQrDssuBFHS" +
                                     "8Xww8iJ30Ky85yINb1vlLpY6cRnH1ZoZ4ge7tnIMN9xtkC3Unaq3AEKSp6AVzrhY1e" +
                                     "IGCPzfzLNsO8ABLDR2SjTURLSvQCzNIlvS01JiM5SQK6vCLgn52a4IaEtJGI6mcN0R" +
                                     "3xD7jHolsVYOcfX1aPAJnYHK1FxEC85cnQW3s5lEmxSD6XQAD3n48o4k8Xe8zQ5";

    private final UserRepository repository;
    private final UserJWTMapper userJWTMapper;

    public TestController(UserRepository userRepository, UserJWTMapper userJWTMapper) {
        this.repository = userRepository;
        this.userJWTMapper = userJWTMapper;
    }

    @GetMapping
    public void test() {
        var user = repository.save(new User("test_domain", RND_HASH, UserRole.CUSTOMER));
        var token = userJWTMapper.create(user);
        var parsedData = userJWTMapper.parse(token.getToken());
    }
}

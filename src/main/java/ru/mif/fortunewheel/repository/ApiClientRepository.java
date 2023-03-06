package ru.mif.fortunewheel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mif.fortunewheel.domain.APIClient;

import java.util.Optional;

@Repository
public interface ApiClientRepository extends JpaRepository<APIClient, Long> {

    Optional<APIClient> findTopByDomainAndAgent(String domain, String agent);
}

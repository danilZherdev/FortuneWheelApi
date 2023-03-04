package ru.mif.fortunewheel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mif.fortunewheel.domain.SpinPrize;

@Repository
public interface SpinPrizeRepository extends JpaRepository<SpinPrize, Long> {
}

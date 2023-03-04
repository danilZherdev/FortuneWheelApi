package ru.mif.fortunewheel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mif.fortunewheel.domain.Prize;

@Repository
public interface PrizeRepository extends JpaRepository<Prize, Long> {
}

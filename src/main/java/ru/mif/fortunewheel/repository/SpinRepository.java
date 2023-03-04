package ru.mif.fortunewheel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mif.fortunewheel.domain.Spin;

@Repository
public interface SpinRepository extends JpaRepository<Spin, Long> {
}

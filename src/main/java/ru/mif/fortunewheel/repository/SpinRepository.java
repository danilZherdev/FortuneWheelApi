package ru.mif.fortunewheel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mif.fortunewheel.domain.Spin;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.enums.SpinStatusType;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpinRepository extends JpaRepository<Spin, Long> {

    Optional<Spin> findByIdAndStatus(long id, SpinStatusType status);
    int countByUserAndStatus(User user, SpinStatusType status);
    Page<Spin> findAllByUserAndStatus(User user, SpinStatusType status, Pageable pageable);
    Page<Spin> findAllByUser(User user, Pageable pageable);
    Optional<Spin> findFirstByUserAndStatus(User user, SpinStatusType status);
}

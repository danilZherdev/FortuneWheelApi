package ru.mif.fortunewheel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mif.fortunewheel.domain.SpinPrize;
import ru.mif.fortunewheel.domain.User;

@Repository
public interface SpinPrizeRepository extends JpaRepository<SpinPrize, Long> {

    Page<SpinPrize> findAllBySpin_User(User user, Pageable pageable);
}

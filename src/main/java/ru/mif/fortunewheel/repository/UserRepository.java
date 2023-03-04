package ru.mif.fortunewheel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mif.fortunewheel.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

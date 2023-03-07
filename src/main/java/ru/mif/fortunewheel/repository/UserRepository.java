package ru.mif.fortunewheel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.enums.UserRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByHash(String hash);
    Optional<User> findByIdAndRole(long id, UserRole role);
    Optional<User> findByEmailAndHashAndRole(String email, String hash, UserRole role);
    Optional<User> findByEmailAndHashAndRoleIn(String email, String hash, UserRole... role);
    Page<User> findAllByRole(UserRole role, Pageable pageable);
}

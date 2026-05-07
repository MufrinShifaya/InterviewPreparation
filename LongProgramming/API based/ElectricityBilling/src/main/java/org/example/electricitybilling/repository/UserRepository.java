package org.example.electricitybilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.electricitybilling.entity.User;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhone(String phone);
}
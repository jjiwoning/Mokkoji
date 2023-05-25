package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);
}

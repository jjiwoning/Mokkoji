package com.ssafy.Mokkoji.core.user.repository;

import com.ssafy.Mokkoji.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);
}

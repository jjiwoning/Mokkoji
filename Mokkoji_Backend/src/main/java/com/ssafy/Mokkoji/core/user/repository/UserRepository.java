package com.ssafy.Mokkoji.core.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.user.domain.vo.LoginId;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

	Optional<User> findByLoginId(LoginId loginId);

	boolean existsByLoginId(LoginId loginId);
}

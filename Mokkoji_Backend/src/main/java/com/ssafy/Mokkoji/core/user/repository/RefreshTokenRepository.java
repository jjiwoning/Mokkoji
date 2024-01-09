package com.ssafy.Mokkoji.core.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.ssafy.Mokkoji.core.user.domain.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}

package com.ssafy.Mokkoji.core.user.repository;

import com.ssafy.Mokkoji.core.user.domain.User;
import com.ssafy.Mokkoji.core.user.dto.request.UserSearchRequest;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> searchAllUser(UserSearchRequest userSearchRequest);
}

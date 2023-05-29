package com.ssafy.Mokkoji.repository;

import com.ssafy.Mokkoji.domain.User;
import com.ssafy.Mokkoji.dto.request.UserSearch;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> searchAllUser(UserSearch userSearch);
}

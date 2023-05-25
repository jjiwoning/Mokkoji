package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.User;
import com.ssafy.enjoytrip.dto.request.UserSearch;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> searchAllUser(UserSearch userSearch);
}

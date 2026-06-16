package com.suraj.projects.lovable_clone.mapper;

import com.suraj.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.suraj.projects.lovable_clone.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserProfileResponse toUserProfileResponse(User user);
}

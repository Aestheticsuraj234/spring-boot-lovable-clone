package com.suraj.projects.lovable_clone.service;

import com.suraj.projects.lovable_clone.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}

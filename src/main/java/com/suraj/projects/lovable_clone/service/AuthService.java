package com.suraj.projects.lovable_clone.service;

import com.suraj.projects.lovable_clone.dto.auth.AuthResponse;
import com.suraj.projects.lovable_clone.dto.auth.LoginRequest;
import com.suraj.projects.lovable_clone.dto.auth.SignupRequest;
import org.jspecify.annotations.Nullable;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}

package com.suraj.projects.lovable_clone.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank String username,
        @NotBlank String name,
        @NotBlank @Size(min = 8) String password
) {
}

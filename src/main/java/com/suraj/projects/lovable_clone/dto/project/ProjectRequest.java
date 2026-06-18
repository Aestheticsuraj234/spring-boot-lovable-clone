package com.suraj.projects.lovable_clone.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectRequest(
        @NotBlank @Size(max = 255) String name
) {
}

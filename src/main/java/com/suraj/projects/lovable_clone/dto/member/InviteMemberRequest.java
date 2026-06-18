package com.suraj.projects.lovable_clone.dto.member;

import com.suraj.projects.lovable_clone.enums.ProjectRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviteMemberRequest(
        @NotBlank String username,
        @NotNull ProjectRole role
) {
}

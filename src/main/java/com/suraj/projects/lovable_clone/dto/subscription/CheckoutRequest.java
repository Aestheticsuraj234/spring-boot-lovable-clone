package com.suraj.projects.lovable_clone.dto.subscription;

import jakarta.validation.constraints.NotNull;

public record CheckoutRequest(@NotNull Long planId) {
}

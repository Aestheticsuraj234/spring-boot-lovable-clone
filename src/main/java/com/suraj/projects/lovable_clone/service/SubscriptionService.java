package com.suraj.projects.lovable_clone.service;

import com.suraj.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.suraj.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.suraj.projects.lovable_clone.dto.subscription.PortalResponse;
import com.suraj.projects.lovable_clone.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}

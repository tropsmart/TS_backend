package com.softper.ts.services;

import com.softper.ts.models.Subscription;
import com.softper.ts.resources.comunications.SubscriptionResponse;

public interface ISubscriptionService extends ICrudService<Subscription> {
    SubscriptionResponse findSubscriptionById(int subscriptionId);
    SubscriptionResponse subscribe(int userId, int planId);
    SubscriptionResponse findSubscriptionsByUserId(int userId);
    SubscriptionResponse findAllSubscriptions();
    SubscriptionResponse cancelSubscription(int subscriptionId);
    SubscriptionResponse enableSubscriptionById(int subscriptionId);
    SubscriptionResponse deleteSubscriptionBySubscriptionId(int subscriptionId);
}

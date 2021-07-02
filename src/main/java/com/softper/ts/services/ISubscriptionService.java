package com.softper.ts.services;

import com.softper.ts.models.Subscription;
import com.softper.ts.resources.comunications.BaseResponse;

public interface ISubscriptionService extends ICrudService<Subscription> {
    BaseResponse findSubscriptionById(int subscriptionId);
    BaseResponse subscribe(int userId, int planId);
    BaseResponse findSubscriptionsByUserId(int userId);
    BaseResponse findAllSubscriptions();
    BaseResponse cancelSubscription(int subscriptionId);
    BaseResponse enableSubscriptionById(int subscriptionId);
    BaseResponse disableSubscriptionById(int subscriptionId);
    BaseResponse deleteSubscriptionBySubscriptionId(int subscriptionId);
}

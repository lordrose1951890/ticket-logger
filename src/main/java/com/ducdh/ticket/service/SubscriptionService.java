package com.ducdh.ticket.service;

import com.ducdh.ticket.entity.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    List<Subscription> getListSubscription();

    Optional<Subscription> getSubscriptionById(Long id);

    Subscription save(Subscription subscription);

    Subscription update(Subscription subscription);

    void deleteSubscription(Long id);

    int getCount();
}

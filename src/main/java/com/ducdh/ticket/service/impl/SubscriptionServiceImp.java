package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Subscription;
import com.ducdh.ticket.service.SubscriptionService;

import java.util.List;
import java.util.Optional;

public class SubscriptionServiceImp implements SubscriptionService {
    @Override
    public List<Subscription> getListSubscription() {
        return null;
    }

    @Override
    public Optional<Subscription> getSubscriptionById(Long id) {
        return Optional.empty();
    }

    @Override
    public Subscription save(Subscription subscription) {
        return null;
    }

    @Override
    public Subscription update(Subscription subscription) {
        return null;
    }

    @Override
    public void deleteSubscription(Long id) {

    }

    @Override
    public int getCount() {
        return 0;
    }
}

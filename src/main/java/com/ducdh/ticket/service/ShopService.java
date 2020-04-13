package com.ducdh.ticket.service;

import com.ducdh.ticket.entity.Shop;
import com.ducdh.ticket.model.request.ShopRequest;

import java.util.List;
import java.util.Optional;

public interface ShopService {
    List<Shop> findAll();

    Shop findByShopId(Long id);

    Shop findByUserId(Long userId);

    Shop save(ShopRequest shop);

    Shop update(Shop shop);

    void deleteShopById(Long id);
}

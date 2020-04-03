package com.ducdh.ticket.service;

import com.ducdh.ticket.entity.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopService {
    List<Shop> findByShopName(String shopName);

    Optional<Shop> findByShopId(Long id);

    Shop save(Shop shop);

    Shop update(Shop shop);

    void deleteShopById(Long id);
}

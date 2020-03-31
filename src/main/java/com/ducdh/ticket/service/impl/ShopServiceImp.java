package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Shop;
import com.ducdh.ticket.service.ShopService;

import java.util.List;
import java.util.Optional;

public class ShopServiceImp implements ShopService {
    @Override
    public List<Shop> findByShopName(String shopName) {
        return null;
    }

    @Override
    public Optional<Shop> findByShopId(Long id) {
        return Optional.empty();
    }

    @Override
    public Shop save(Shop shop) {
        return null;
    }

    @Override
    public Shop update(Shop shop) {
        return null;
    }

    @Override
    public void deleteShopById(Long id) {

    }

    @Override
    public int getCount() {
        return 0;
    }
}

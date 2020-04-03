package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Shop;
import com.ducdh.ticket.repository.ShopRepository;
import com.ducdh.ticket.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopServiceImp implements ShopService {

    private final ShopRepository shopRepository;

    @Override
    public List<Shop> findByShopName(String shopName) {
        return shopRepository.getAllByShopName(shopName);
    }

    @Override
    public Optional<Shop> findByShopId(Long id) {
        return shopRepository.findById(id);
    }

    @Override
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop update(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public void deleteShopById(Long id) {
        shopRepository.deleteById(id);
    }

}

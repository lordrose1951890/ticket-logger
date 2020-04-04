package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Shop;
import com.ducdh.ticket.model.exception.ResourceNotFoundException;
import com.ducdh.ticket.repository.ShopRepository;
import com.ducdh.ticket.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
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
        return shopRepository.findById(shop.getId()).map(
                shop1 -> shopRepository.save(shop)).orElseThrow(() -> new ResourceNotFoundException("Not found: " + shop.getId()));
    }

    @Override
    public void deleteShopById(Long id) {
        shopRepository.findById(id).map(shop -> {
            shopRepository.deleteById(id);
            return shop;
        }).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
    }

}

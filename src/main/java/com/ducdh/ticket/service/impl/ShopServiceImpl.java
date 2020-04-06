package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Shop;
import com.ducdh.ticket.model.exception.ResourceNotFoundException;
import com.ducdh.ticket.model.request.ShopRequest;
import com.ducdh.ticket.repository.ShopRepository;
import com.ducdh.ticket.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    public Shop findByShopId(Long id) {
        return shopRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Shop not found: " + id));
    }

    @Override
    public Shop save(ShopRequest shopRequest) {
        Shop shop = new Shop();
        shop.setShopName(shopRequest.getShopName());
        shop.setShopAddress(shopRequest.getShopAddress());
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

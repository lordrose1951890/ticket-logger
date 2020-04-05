package com.ducdh.ticket.controller;

import com.ducdh.ticket.constant.Api;
import com.ducdh.ticket.entity.Shop;
import com.ducdh.ticket.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.ROOT_URL + "/shops")
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public ResponseEntity<?> getAllShops() {
        return ResponseEntity.ok(shopService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneShop(@PathVariable Long id) {
        return ResponseEntity.ok(shopService.findByShopId(id));
    }

    @PostMapping
    public Shop createShop(@RequestBody Shop shop) throws Exception {
        return shopService.save(shop);
    }

    @PutMapping
    public Shop updateShop(@RequestBody Shop shop) throws Exception {
        return shopService.update(shop);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteShop(@PathVariable Long id) throws Exception {
        shopService.deleteShopById(id);
        return ResponseEntity.ok().build();
    }
}

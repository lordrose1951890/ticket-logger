package com.ducdh.ticket.controller;

import com.ducdh.ticket.entity.Shop;
import com.ducdh.ticket.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/shops")
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/{shopName}")
    public ResponseEntity<?> listOfShop(@PathVariable String shopName){
        return ResponseEntity.ok(shopService.findByShopName(shopName));
    }

    @GetMapping("/{id}")
    public ResponseEntity oneShop(@PathVariable Long id){
        return ResponseEntity.ok(shopService.findByShopId(id));
    }

    @PostMapping
    public Shop shopAddition(@RequestBody Shop shop){
        return shopService.save(shop);
    }

    @PutMapping
    public Shop shopUpdatable(@RequestBody Shop shop){
        return shopService.update(shop);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteShop(@PathVariable Long id){
        if (shopService.findByShopId(id).isPresent()){
            shopService.deleteShopById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

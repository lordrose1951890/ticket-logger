package com.ducdh.ticket.controller;

import com.ducdh.ticket.entity.Shop;
import com.ducdh.ticket.model.request.ShopRequest;
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
    public ResponseEntity<?> getAllShopByName(@PathVariable String shopName){
        return ResponseEntity.ok(shopService.findByShopName(shopName));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneShop(@PathVariable Long id){
        return ResponseEntity.ok(shopService.findByShopId(id));
    }

    @PostMapping
    public Shop createShop(@RequestBody ShopRequest shop) throws Exception{
        return shopService.save(shop);
    }

    @PutMapping
    public Shop updateShop(@RequestBody Shop shop) throws Exception{
        return shopService.update(shop);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteShop(@PathVariable Long id) throws Exception{
        shopService.deleteShopById(id);
        return ResponseEntity.ok().build();
    }
}

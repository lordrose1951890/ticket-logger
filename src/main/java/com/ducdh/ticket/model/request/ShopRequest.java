package com.ducdh.ticket.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShopRequest implements Serializable {
    private String shopName;

    private String shopAddress;
}

package com.example.stock.domain;

import jakarta.persistence.*;

@Entity
public class Stock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Integer quantity;

    @Version
    private Long version;

    // 생성 단축키 = Alt + Insert
    public Stock() {
    }
    public Stock(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void decrease(Integer quantity) {
        if(this.quantity - quantity < 0) {
            throw new RuntimeException("재고는 0개 미만이 될 수 없음");
        }
        this.quantity -= quantity;
    }
}

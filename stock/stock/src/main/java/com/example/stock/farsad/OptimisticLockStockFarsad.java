package com.example.stock.farsad;

import com.example.stock.service.OptimisticLockStockService;
import org.springframework.stereotype.Component;

@Component
public class OptimisticLockStockFarsad {
    private final OptimisticLockStockService optimisticLockStockService;

    public OptimisticLockStockFarsad(OptimisticLockStockService optimisticLockStockService) {
        this.optimisticLockStockService = optimisticLockStockService;
    }

    public void decrease(Long id, Integer quantity) throws InterruptedException {
        while(true) {
            try{
                optimisticLockStockService.decrease(id, quantity);
                break;
            } catch (Exception ex) {
                Thread.sleep(50);
            }
        }
    }
}

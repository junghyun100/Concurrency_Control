package com.example.stock.facade;

import com.example.stock.service.StockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLockStockFacade {
    // 별도의 library를 사용함
    private RedissonClient  redissonClient;
    private StockService stockService;

    public RedissonLockStockFacade(RedissonClient redissonClient, StockService stockService) {
        this.redissonClient = redissonClient;
        this.stockService = stockService;
    }
    public void decrease (Long id, Integer quantity) {
        RLock lock = redissonClient.getLock(id.toString());
        try {
            boolean available = lock.tryLock(20, 1, TimeUnit.SECONDS);
            if(!available) {
                System.out.println("lock fail");
                return;
            }
            stockService.decrease(id,quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}

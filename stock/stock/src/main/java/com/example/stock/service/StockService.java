package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void decrease(Long id, Integer quantity) {
        // 조회
        Stock stock = stockRepository.findById(id).orElseThrow();
        // 감소
        stock.decrease(quantity);
        // 저장
        stockRepository.saveAndFlush(stock);
    }
}

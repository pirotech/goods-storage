package com.example.storage.repos;

import com.example.storage.domain.Goods;
import org.springframework.data.repository.CrudRepository;

public interface GoodsRepository extends CrudRepository<Goods, Long> {

    Goods findByName(String name);
}

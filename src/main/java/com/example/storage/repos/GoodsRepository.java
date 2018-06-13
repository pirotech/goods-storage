package com.example.storage.repos;

import com.example.storage.domain.Category;
import com.example.storage.domain.Goods;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GoodsRepository extends CrudRepository<Goods, Long> {

    List<Goods> findByNameAndCategory(String name, Category category);
}

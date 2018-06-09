package com.example.storage.controllers.api;

import com.example.storage.domain.Goods;
import com.example.storage.repos.GoodsRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goods")
public class GoodsRestController {

    private GoodsRepository repository;

    public GoodsRestController(GoodsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Goods> getGoods() {
        return repository.findAll();
    }

    @PostMapping
    public void addGoods(@RequestBody Goods goods) {
        Goods found = repository.findByName(goods.getName());
        if (found == null) {
            repository.save(goods);
        }
    }

    @PatchMapping("/{id}")
    public void updateGoods(@PathVariable Long id, @RequestBody Goods goods) {
        Goods found = repository.findById(id).orElse(null);
        if (found != null) {
            found.setId(id);

            found.setName(goods.getName() != null
                    ? goods.getName()
                    : found.getName());
            found.setDescription(goods.getDescription() != null
                    ? goods.getDescription()
                    : found.getDescription());
            found.setPrice(goods.getPrice() != null
                    ? goods.getPrice()
                    : found.getPrice());
            found.setCount(goods.getCount() != null
                    ? goods.getCount()
                    : found.getCount());
            found.setCategory(goods.getCategory() != null
                    ? goods.getCategory()
                    : found.getCategory());

            repository.save(found);
        }
    }

    @DeleteMapping("/{id}")
    public void removeGoods(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

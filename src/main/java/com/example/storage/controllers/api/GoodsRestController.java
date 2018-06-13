package com.example.storage.controllers.api;

import com.example.storage.domain.Goods;
import com.example.storage.repos.GoodsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> addGoods(@RequestBody Goods goods) {
        List<Goods> sameNameAndCategory = repository.findByNameAndCategory(goods.getName(), goods.getCategory());
        if (sameNameAndCategory.isEmpty()) {
            repository.save(goods);
            return new ResponseEntity<>(goods, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(goods, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateGoods(@PathVariable Long id, @RequestBody Goods goods) {
        Goods found = repository.findById(id).orElse(null);
        if (found == null) {
            return new ResponseEntity<>(goods, HttpStatus.BAD_REQUEST);
        }

        boolean nameIsChanged = !found.getName().equals(goods.getName());
        if (nameIsChanged) {
            List<Goods> sameNameAndCategory = repository.findByNameAndCategory(goods.getName(), goods.getCategory());
            if (sameNameAndCategory.isEmpty()) {
                acceptUpdates(found, goods);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            acceptUpdates(found, goods);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(goods, HttpStatus.BAD_REQUEST);
    }

    private void acceptUpdates(Goods old, Goods changed) {
        old.setName(changed.getName() != null
                ? changed.getName()
                : old.getName());
        old.setDescription(changed.getDescription() != null
                ? changed.getDescription()
                : old.getDescription());
        old.setPrice(changed.getPrice() != null
                ? changed.getPrice()
                : old.getPrice());
        old.setCount(changed.getCount() != null
                ? changed.getCount()
                : old.getCount());
        old.setCategory(changed.getCategory() != null
                ? changed.getCategory()
                : old.getCategory());

        repository.save(old);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeGoods(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.example.storage.controllers.api;

import com.example.storage.domain.Category;
import com.example.storage.repos.CategoryRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoriesRestController {

    private CategoryRepository repository;

    public CategoriesRestController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Category> getCategories() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        Category found = repository.findByName(category.getName());
        if (found == null && category.getName() != null) {
            repository.save(category);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(category, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category found = repository.findById(id).orElse(null);
        Category sameName = repository.findByName(category.getName());
        if (found != null && sameName == null) {
            found.setId(id);

            found.setName(category.getName() != null
                    ? category.getName()
                    : found.getName());
            found.setDescription(category.getDescription() != null
                    ? category.getDescription()
                    : found.getDescription());
            found.setGoods(category.getGoods() != null
                    ? category.getGoods()
                    : found.getGoods());

            repository.save(found);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(category, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCategory(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

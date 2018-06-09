package com.example.storage.controllers.api;

import com.example.storage.domain.Category;
import com.example.storage.repos.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public void addCategory(@RequestBody Category category) {
        repository.save(category);
    }

    @PatchMapping("/{id}")
    public void updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category found = repository.findById(id).orElse(null);
        if (found != null) {
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
        }
    }

    @DeleteMapping("/{id}")
    public void removeCategory(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

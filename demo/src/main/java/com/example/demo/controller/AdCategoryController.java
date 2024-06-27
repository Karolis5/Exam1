package com.example.demo.controller;

import com.example.demo.category.AdCategory;
import com.example.demo.category.AdCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ad-categories")
public class AdCategoryController {

    @Autowired
    private AdCategoryService adCategoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody AdCategory category) {
        AdCategory createdCategory = adCategoryService.createCategory(category);
        if (createdCategory != null) {
            return ResponseEntity.ok(createdCategory);
        } else {
            return ResponseEntity.badRequest().body("Category already exists with name: " + category.getName());
        }
    }

    @GetMapping
    public ResponseEntity<List<AdCategory>> getAllCategories() {
        List<AdCategory> categories = adCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        boolean deleted = adCategoryService.deleteCategory(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody AdCategory category) {
        AdCategory updatedCategory = adCategoryService.updateCategory(id, category);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

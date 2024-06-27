package com.example.demo.category;

import com.example.demo.category.AdCategory;
import com.example.demo.category.AdCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdCategoryService {

    @Autowired
    private AdCategoryRepository adCategoryRepository;

    public List<AdCategory> getAllCategories() {
        return adCategoryRepository.findAll();
    }

    public AdCategory getCategoryById(String id) {
        return adCategoryRepository.findById(id).orElse(null);
    }

    public AdCategory createCategory(AdCategory category) {
        // Check if category with the same name already exists
        AdCategory existingCategory = adCategoryRepository.findByName(category.getName());
        if (existingCategory != null) {
            throw new RuntimeException("Category with name " + category.getName() + " already exists.");
        }
        return adCategoryRepository.save(category);
    }

    public AdCategory updateCategory(String id, AdCategory category) {
        AdCategory existingCategory = adCategoryRepository.findById(id).orElse(null);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            return adCategoryRepository.save(existingCategory);
        }
        return null; // Handle not found scenario
    }


    public boolean deleteCategory(String id) {
        if (adCategoryRepository.existsById(id)) {
            adCategoryRepository.deleteById(id);
            return true;
        }
        return false; // Handle not found scenario
    }
}

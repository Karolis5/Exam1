package com.example.demo.category;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdCategoryRepository extends MongoRepository<AdCategory, String> {
    AdCategory findByName(String name);
}

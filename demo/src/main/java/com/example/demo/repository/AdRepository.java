package com.example.demo.repository;

import com.example.demo.model.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends MongoRepository<Ad, String> {

    List<Ad> findByCategoryId(String categoryId); // Updated method name
}

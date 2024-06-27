package com.example.demo.controller;

import com.example.demo.model.Ad;
import com.example.demo.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping
    public ResponseEntity<List<Ad>> getAllAds() {
        try {
            List<Ad> ads = adService.getAllAds();
            return ResponseEntity.ok(ads);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Handle error appropriately
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ad> getAdById(@PathVariable String id) {
        try {
            Ad ad = adService.getAdById(id);
            if (ad != null) {
                return ResponseEntity.ok(ad);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Handle error appropriately
        }
    }

    @PostMapping
    public ResponseEntity<Ad> createAd(@RequestBody Ad ad) {
        try {
            Ad createdAd = adService.createAd(ad);
            return ResponseEntity.ok(createdAd);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Handle error appropriately
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable String id, @RequestBody Ad ad) {
        try {
            Ad updatedAd = adService.updateAd(id, ad);
            if (updatedAd != null) {
                return ResponseEntity.ok(updatedAd);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Handle error appropriately
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable String id) {
        try {
            boolean deleted = adService.deleteAd(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Handle error appropriately
        }
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<Ad>> getAdsByCategoryId(@PathVariable String categoryId) {
        try {
            List<Ad> ads = adService.getAdsByCategoryId(categoryId);
            return ResponseEntity.ok(ads);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Handle error appropriately
        }
    }

    @DeleteMapping("/{id}/comments")
    public ResponseEntity<Ad> deleteComment(@PathVariable String id, @RequestParam String comment) {
        try {
            Ad ad = adService.getAdById(id);
            if (ad != null && ad.getComments().contains(comment)) {
                ad.getComments().remove(comment);
                adService.updateAd(id, ad); // Save the updated ad
                return ResponseEntity.ok(ad);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Handle error appropriately
        }
    }
}

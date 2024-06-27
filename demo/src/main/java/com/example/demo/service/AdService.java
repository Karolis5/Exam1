package com.example.demo.service;

import com.example.demo.model.Ad;
import com.example.demo.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdService {

    @Autowired
    private AdRepository adRepository;

    public List<Ad> getAllAds() {
        try {
            return adRepository.findAll();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Failed to fetch all ads", e);
        }
    }

    public Ad getAdById(String id) {
        try {
            Optional<Ad> ad = adRepository.findById(id);
            return ad.orElse(null);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Failed to fetch ad with id: " + id, e);
        }
    }

    public Ad createAd(Ad ad) {
        try {
            return adRepository.save(ad);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Failed to create ad", e);
        }
    }

    public Ad updateAd(String id, Ad ad) {
        try {
            if (adRepository.existsById(id)) {
                ad.setId(id); // Ensure the ad ID is set for update
                return adRepository.save(ad);
            }
            return null; // Handle update failure scenario
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Failed to update ad with id: " + id, e);
        }
    }

    public boolean deleteAd(String id) {
        try {
            if (adRepository.existsById(id)) {
                adRepository.deleteById(id);
                return true;
            }
            return false; // Handle deletion failure scenario
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Failed to delete ad with id: " + id, e);
        }
    }

    public List<Ad> getAdsByCategoryId(String categoryId) {
        try {
            return adRepository.findByCategoryId(categoryId);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Failed to fetch ads by category id: " + categoryId, e);
        }
    }

    public List<String> getCommentsByAdId(String id) {
        try {
            Ad ad = adRepository.findById(id).orElse(null);
            if (ad != null) {
                return ad.getComments();
            }
            return null; // Handle not found scenario
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Failed to fetch comments for ad with id: " + id, e);
        }
    }

    public boolean deleteComment(String id, String comment) {
        try {
            Ad ad = adRepository.findById(id).orElse(null);
            if (ad != null && ad.getComments().contains(comment)) {
                ad.getComments().remove(comment);
                adRepository.save(ad); // Save the updated ad
                return true;
            }
            return false; // Handle deletion failure scenario
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Failed to delete comment for ad with id: " + id, e);
        }
    }
}

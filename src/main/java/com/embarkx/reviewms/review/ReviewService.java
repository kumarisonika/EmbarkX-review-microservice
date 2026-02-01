package com.embarkx.reviewms.review;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;


    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;

    }

    List<Review> getAllReviews(Long companyId){
        return reviewRepository.findByCompanyId(companyId);

    }


    boolean addReview(Long companyId, Review review){
        try {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    Review getReview(Long reviewId){
       return reviewRepository.findById(reviewId).orElse(null);
    }


    public boolean updateReview(Long reviewId, Review updatedReview){
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }


    public boolean deleteReview(Long reviewId){
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }



}

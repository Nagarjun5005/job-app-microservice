package com.reviewms.review;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{


   private final ReviewRepository  reviewRepository;



    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;

    }



    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review>reviewList=reviewRepository.findByCompanyId(companyId);
        return reviewList;
    }


    @Override
    public boolean createReview(Long companyId, Review review) {

      if(companyId!=null && review!=null){
          review.setCompanyId(companyId);
          reviewRepository.save(review);
          return true;
      }
      return false;
    }

    @Override
    public Review getReview( Long reviewId) {
      return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview( Long reviewId, Review updateReview) {
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            review.setTitle(updateReview.getTitle());
            review.setDescription(updateReview.getDescription());
            review.setRating(updateReview.getRating());
            review.setCompanyId(updateReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }


    }

    @Override
    public boolean deleteReview( Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}

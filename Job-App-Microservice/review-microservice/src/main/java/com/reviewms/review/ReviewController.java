package com.reviewms.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        List<Review> allReviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(allReviews, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<String>createReview(@RequestParam Long companyId,@RequestBody  Review review){
        boolean reviewSaved= reviewService.createReview(companyId, review);
        if(reviewSaved){
            return new ResponseEntity<>("created review successfully",HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>("not saved ",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review>getReview(@PathVariable Long reviewId){
        Review review = reviewService.getReview(reviewId);
        return new ResponseEntity<>( review,HttpStatus.OK);
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<String>updateReview(@PathVariable Long reviewId,@RequestBody Review review){
        boolean updateReview = reviewService.updateReview( reviewId, review);
        if(updateReview){
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("review or company not sound",HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String>deleteReview(@PathVariable Long reviewId){
        boolean deleteReview= reviewService.deleteReview(reviewId);
        if(deleteReview){
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("review or company not found",HttpStatus.NOT_FOUND);
    }

}

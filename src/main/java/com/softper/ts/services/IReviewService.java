package com.softper.ts.services;

import com.softper.ts.models.Review;
import com.softper.ts.resources.comunications.ReviewResponse;
import com.softper.ts.resources.inputs.ReviewInput;

public interface IReviewService extends ICrudService<Review> {
    ReviewResponse findAllReviews();
    ReviewResponse findReviewsByCustomerId(int customerId);
    ReviewResponse findReviewsByDriverId(int driverId);
    ReviewResponse findReviewById(int reviewId);
    ReviewResponse addReviewByCargoId(int cargoId, ReviewInput reviewInput);
}

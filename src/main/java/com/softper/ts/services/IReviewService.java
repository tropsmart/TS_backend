package com.softper.ts.services;

import com.softper.ts.models.Review;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.ReviewInput;

public interface IReviewService extends ICrudService<Review> {
    BaseResponse findAllReviews();
    BaseResponse findReviewsByCustomerId(int customerId);
    BaseResponse findReviewsByDriverId(int driverId);
    BaseResponse findReviewById(int reviewId);
    BaseResponse addReviewByCargoId(int cargoId, ReviewInput reviewInput);
}

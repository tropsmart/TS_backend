package com.softper.ts.servicesImp;

import com.softper.ts.models.Cargo;
import com.softper.ts.models.Qualification;
import com.softper.ts.models.Review;
import com.softper.ts.repositories.ICargoRepository;
import com.softper.ts.repositories.IQualificationRepository;
import com.softper.ts.repositories.IReviewRepository;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.ReviewInput;
import com.softper.ts.resources.outputs.ReviewOutput;
import com.softper.ts.services.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private IReviewRepository reviewRepository;
    @Autowired
    private ICargoRepository cargoRepository;
    @Autowired
    private IQualificationRepository qualificationRepository;

    @Override
    public BaseResponse findAllReviews() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Review> reviews = reviewRepository.findAll();
            List<ReviewOutput> reviewOutputList = new ArrayList<>();
            for (Review r:reviews) {
                ReviewOutput newReviewOutput = new ReviewOutput();
                newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
                newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
                newReviewOutput.setCargo(r.getCargo().getDescription());
                newReviewOutput.setCommentary(r.getCommentary());
                newReviewOutput.setCalification(r.getCalification());
                reviewOutputList.add(newReviewOutput);
            }
            response = new BaseResponse("findAllReviews","success",1);
            response.setReviewOutputList(reviewOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllReviews","An error ocurred while getting the review list : "+e.getMessage(),-2);
        }
    }


    @Override
    public BaseResponse findReviewsByCustomerId(int customerId) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Review> reviews = reviewRepository.findReviewsByCustomerId(customerId);
            List<ReviewOutput> reviewOutputList = new ArrayList<>();
            for (Review r:reviews) {
                ReviewOutput newReviewOutput = new ReviewOutput();
                newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
                newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
                newReviewOutput.setCargo(r.getCargo().getDescription());
                newReviewOutput.setCommentary(r.getCommentary());
                newReviewOutput.setCalification(r.getCalification());
                reviewOutputList.add(newReviewOutput);
            }
            response = new BaseResponse("findReviewsByCustomerId","success",1);
            response.setReviewOutputList(reviewOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findReviewsByCustomerId","An error ocurred while getting the review list : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findReviewsByDriverId(int driverId) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Review> reviews = reviewRepository.findReviewsByDriverId(driverId);
            List<ReviewOutput> reviewOutputList = new ArrayList<>();
            for (Review r:reviews) {
                ReviewOutput newReviewOutput = new ReviewOutput();
                newReviewOutput.setCustomer(r.getCargo().getCustomer().getPerson().getFirstName()+" "+r.getCargo().getCustomer().getPerson().getLastName());
                newReviewOutput.setDriver(r.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+r.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
                newReviewOutput.setCargo(r.getCargo().getDescription());
                newReviewOutput.setCommentary(r.getCommentary());
                newReviewOutput.setCalification(r.getCalification());
                reviewOutputList.add(newReviewOutput);
            }
            response = new BaseResponse("findReviewsByDriverId","success",1);
            response.setReviewOutputList(reviewOutputList);
            return response;        
        }
        catch (Exception e)
        {
            return new BaseResponse("findReviewsByDriverId","An error ocurred while getting the review list : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findReviewById(int reviewId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Review getReview = reviewRepository.findById(reviewId).get();
            ReviewOutput newReviewOutput = new ReviewOutput();
            newReviewOutput.setCustomer(getReview.getCargo().getCustomer().getPerson().getFirstName()+" "+getReview.getCargo().getCustomer().getPerson().getLastName());
            newReviewOutput.setDriver(getReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+getReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
            newReviewOutput.setCargo(getReview.getCargo().getDescription());
            newReviewOutput.setCommentary(getReview.getCommentary());
            newReviewOutput.setCalification(getReview.getCalification());
            
            response = new BaseResponse("findReviewById","success",1);
            response.setReviewOutput(newReviewOutput);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findReviewById","An error ocurred while getting the review list : "+e.getMessage(),-2);

        }

    }

    @Override
    public BaseResponse addReviewByCargoId(int cargoId, ReviewInput reviewInput) {
        BaseResponse response = new BaseResponse();
        try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            Qualification getQualification = qualificationRepository.findQualificationByDriverId(getCargo.getService().getServicesRequest().getDriver().getId());
            Review newReview = new Review();
            newReview.setCommentary(reviewInput.getCommentary());
            newReview.setCalification(reviewInput.getCalification());
            newReview.setCargo(getCargo);
            newReview.setQualification(getQualification);

            newReview = reviewRepository.save(newReview);

            ReviewOutput newReviewOutput = new ReviewOutput();
            newReviewOutput.setCustomer(newReview.getCargo().getCustomer().getPerson().getFirstName()+" "+newReview.getCargo().getCustomer().getPerson().getLastName());
            newReviewOutput.setDriver(newReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+newReview.getCargo().getService().getServicesRequest().getDriver().getPerson().getLastName());
            newReviewOutput.setCargo(newReview.getCargo().getDescription());
            newReviewOutput.setCommentary(newReview.getCommentary());
            newReviewOutput.setCalification(newReview.getCalification());

            response = new BaseResponse("addReviewByCargoId","success",1);
            response.setReviewOutput(newReviewOutput);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("addReviewByCargoId","An error ocurred while getting the review list : "+e.getMessage(),-2);
        }
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteById(Integer id){
        reviewRepository.deleteById(id);
    }

    @Override
    public Optional<Review> findById(Integer id){
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> findAll(){
        return reviewRepository.findAll();
    }
}
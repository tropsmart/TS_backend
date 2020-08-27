package com.softper.ts.repositories;

import com.softper.ts.models.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IQualificationRepository extends JpaRepository<Qualification, Integer> {
    @Query("select q from Qualification q where q.driver.id = (:driverId)")
    Qualification findQualificationByDriverId(@Param("driverId")int driverId);
}

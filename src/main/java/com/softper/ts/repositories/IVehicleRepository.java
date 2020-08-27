package com.softper.ts.repositories;

import com.softper.ts.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Integer> {
    @Query("select s from Vehicle s where s.driver.id = (:uid)")
    List<Vehicle> getVehiclesByDriverId(@Param("uid") Integer driverId);
}

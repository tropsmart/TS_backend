package com.softper.ts.repositories;

import java.util.List;

import com.softper.ts.models.DriverLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDriverLocationRepository extends JpaRepository<DriverLocation, Integer>{
  
  @Query("select dl from DriverLocation dl where dl.driver.id = (:uid)")
  List<DriverLocation> findDriverLocationByDriverId(@Param("uid") Integer driverId);
}

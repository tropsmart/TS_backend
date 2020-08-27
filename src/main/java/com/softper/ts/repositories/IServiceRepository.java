package com.softper.ts.repositories;

import com.softper.ts.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IServiceRepository extends JpaRepository<Service, Integer> {

    @Query("select s from Service s where s.servicesRequest.driver.id = (:driverId)")
    List<Service> findServicesByDriverId(@Param("driverId")int driverId);
}

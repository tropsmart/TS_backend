package com.softper.ts.repositories;

import com.softper.ts.models.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICargoRepository extends JpaRepository<Cargo , Integer> {
    //Cargo addCargoInCustomer(CargoResource cargoResource);

    @Query("select s from Cargo s where s.customer.id = (:uid)")
    List<Cargo> findCargoesByCustomerId(@Param("uid") Integer customerId);


}

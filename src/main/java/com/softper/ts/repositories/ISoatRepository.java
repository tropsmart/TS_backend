package com.softper.ts.repositories;

import com.softper.ts.models.Soat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISoatRepository extends JpaRepository<Soat, Integer> {
}

package com.softper.ts.repositories;

import com.softper.ts.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRouteRepository extends JpaRepository<Route, Integer> {
}

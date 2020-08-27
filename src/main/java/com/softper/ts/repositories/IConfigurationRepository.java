package com.softper.ts.repositories;

import com.softper.ts.models.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConfigurationRepository extends JpaRepository<Configuration, Integer> {


}

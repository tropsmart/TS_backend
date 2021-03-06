package com.softper.ts.repositories;

import com.softper.ts.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query("select s from User s where s.email =:uid")
    Optional<User> findPersonByEmail(@Param("uid") String userEmail);

    @Query("select s from User s where s.person.id = (:uid)")
    Optional<User> findUserByPersonId(@Param("uid")int personId);

    @Query("select s from User s where s.balance.id = (:uid)")
    Optional<User> findUserByBalanceId(@Param("uid")int balanceId);

    @Query("select s from User s where s.configuration.id = (:uid)")
    Optional<User> findUserByConfigurationId(@Param("uid")int configurationId);

    
    @Query("SELECT s FROM User s WHERE s.person.firstName LIKE CONCAT('%',:dname,'%') AND s.person.personType = 2")
    List<User> findDriverByName(@Param("dname") String name);
}

package com.communication.devtalk.repository;

import com.communication.devtalk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE settings ->> 'theme' = :theme", nativeQuery = true)
    List<User> findByThemeSetting(@Param("theme") String theme);


}

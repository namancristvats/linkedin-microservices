package com.ncv.LinkedInProject.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ncv.LinkedInProject.user_service.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}

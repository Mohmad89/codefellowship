package com.example.app.infrastructure;


import com.example.app.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername (String username);
    List<ApplicationUser> findByFollowing_id (Long id);
}

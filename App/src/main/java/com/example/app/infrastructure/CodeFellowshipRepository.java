package com.example.app.infrastructure;


import com.example.app.domain.CodeFellowship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeFellowshipRepository extends JpaRepository<CodeFellowship, Long> {

    CodeFellowship findByUsername (String username);
}

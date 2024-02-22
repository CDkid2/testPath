package com.pp.repository;

import com.pp.domain.Des;
import com.pp.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {


//    @Query(value="SELECT r FROM Review r ORDER BY r.rvDate DESC")
    List<Review> findByDesCode(Des destination);
}

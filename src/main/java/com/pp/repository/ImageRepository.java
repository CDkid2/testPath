package com.pp.repository;

import com.pp.domain.Des;
import com.pp.domain.Image;
import com.pp.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

    List<Image> findByDesCode(Des destination);

    List<Image> findRvCodeByDesCode(Des destination);
}

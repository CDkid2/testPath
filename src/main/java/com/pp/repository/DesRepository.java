package com.pp.repository;

import com.pp.domain.Des;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface DesRepository extends JpaRepository<Des, String> {

    @Query("SELECT d FROM Des d WHERE d.desName Like %:desName%")
    List<Des> findDesCodeByDesNameContaining(@Param("desName") String desName);

    List<Des> findByDesNameContaining(String desName);

    @Query(value = "SELECT d FROM Des d WHERE d.desClsCode = (SELECT d2.desClsCode FROM Des d2 WHERE d2.desName = :desName)")
    List<Des> findByDClsCodeWithDesName(@Param("desName") String desName);

    List<Des> findByDesClsCode_dClsCode(String dClsCode);

    Des findDestinationByDesCode(String desCode);

    @Query("SELECT d FROM Des d WHERE d.desName LIKE %:keyword%")
    List<Des> findByDesNameLike(@Param("keyword") String keyword);

    List<Des> findTop3ByOrderByDesLikeDesc();
}

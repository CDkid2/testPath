//package com.pp.repository;
//
//import com.pp.domain.AboutBoard;
//import jakarta.transaction.Transactional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//
//public interface AboutBoardRepository extends JpaRepository<AboutBoard, String> {
//    @Query("SELECT DISTINCT a.supTitle FROM AboutBoard a")
//    Set<String> findDistinctSuptitles();
//
//    Optional<AboutBoard> findBySupCode(AboutBoard supCode);
//
//    @Modifying 			// @Query의 sql이 insert/delete/update
//    @Transactional		// commit, rollback
//    @Query("update AboutBoard b set b.supCode=:_seq where b.supCode = :seq")
//    void updateLastSeq(@Param("seq") Long seq);
//
//    @Modifying
//    @Transactional
//    @Query("update AboutBoard b set b.supCode = :newSupCode where b.supCode = :oldSupCode")
//    void updateSupCode(@Param("oldSupCode") String oldSupCode, @Param("newSupCode") String newSupCode);
//
////    List<AboutBoard> findByUserEmail(String userEmail);
//}

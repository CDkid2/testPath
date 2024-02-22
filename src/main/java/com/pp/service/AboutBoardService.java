package com.pp.service;

import com.pp.domain.AboutBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AboutBoardService {

    Page<AboutBoard> findAboutBoardList(Pageable pageable);

    Optional<AboutBoard> findAboutBoardByIdx(String supCode);

    void insertBoard(AboutBoard aboutBoard);

    AboutBoard getAboutBoard(AboutBoard aboutBoard);

    Optional<AboutBoard> findBySupCode(AboutBoard supCode);

    List<AboutBoard> findAboutBoardListByUser(Pageable pageable, String userEmail);
    void addAnswer(String supCode, String supRefs);

    Page<AboutBoard> findAllAboutBoardList(Pageable pageable);


    List<AboutBoard> getAboutBoards(String userEmail, String userRole);
    }







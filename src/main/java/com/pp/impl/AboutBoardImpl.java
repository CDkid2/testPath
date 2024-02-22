//package com.pp.impl;
//
//import com.pp.domain.AboutBoard;
//import com.pp.repository.AboutBoardRepository;
//import com.pp.service.AboutBoardService;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public abstract class AboutBoardImpl implements AboutBoardService {
//
//    @Autowired
//    private AboutBoardRepository aboutBoardRepository;
//    @Autowired
//    public AboutBoardImpl(AboutBoardRepository aboutBoardRepository) {
//        this.aboutBoardRepository = aboutBoardRepository;
//    }
//    @Override
//    public Page<AboutBoard> findAboutBoardList(Pageable pageable) {
//        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
//        return aboutBoardRepository.findAll(pageable);
//    }
//
//    @Override
//    public Optional<AboutBoard> findAboutBoardByIdx(String supCode) {
//        return Optional.of(aboutBoardRepository.findById(supCode).orElse(new AboutBoard()));
//    }
//
//
//    @Override
//    public void insertBoard(AboutBoard board) {
//        board.setSupCode("003");  // sup_code를 '003'으로 설정
//        aboutBoardRepository.save(board); // 게시글을 DB에 저장
//    }
//
//    @Override
//    public AboutBoard getAboutBoard(AboutBoard aboutBoard) {
//        Optional<AboutBoard> findAboutBoard = aboutBoardRepository.findById(aboutBoard.getSupCode());
//        return findAboutBoard.orElse(null);
//    }
//
//    @Override
//    public Optional<AboutBoard> findBySupCode(AboutBoard supCode) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<AboutBoard> findAboutBoardListByUser(Pageable pageable, String id) {
//        // 사용자 아이디를 기준으로 문의글을 필터링하여 가져오는 로직을 구현합니다.
//        // 예시로는 사용자 아이디가 일치하는 문의글만 가져오도록 하였습니다.
//        List<AboutBoard> filteredList = new ArrayList<>();
//        List<AboutBoard> allList = aboutBoardRepository.findAll(pageable).getContent();
//
//        for (AboutBoard board : allList) {
//            if (board.getUserEmail().getUser_email().equals(id)) {
//                filteredList.add(board);
//            }
//        }
//
//        return filteredList;
//    }
//
//    @Override
//    @Transactional
//    public void addAnswer(String supCode, String supRef) {
//        // 게시글을 찾습니다.
//        AboutBoard aboutBoard = aboutBoardRepository.findById(supCode).orElseThrow(
//                () -> new IllegalArgumentException("Invalid supCode: " + supCode));
//
//        // 답변 필드를 업데이트합니다.
//        aboutBoard.setSupRef(supRef);
//
//        // 변경된 내용을 저장합니다.
//        // @Transactional 어노테이션이 있기 때문에 이 부분은 생략할 수도 있습니다.
//        // JPA는 트랜잭션 종료 시 변경 감지(dirty checking) 기능을 통해 변경된 엔티티를 자동으로 DB에 반영합니다.
//        aboutBoardRepository.save(aboutBoard);
//    }
//
////    public List<AboutBoard> getAboutBoards(String userEmail, String userRole) {
////        if ("admin".equals(userRole)) {
////            // 'admin'인 경우 모든 문의글을 반환
////            return aboutBoardRepository.findAll();
////        } else {
////            // 그 외의 경우 해당 사용자의 문의글만 반환
////            return aboutBoardRepository.findById(userEmail);
////        }
////    }
//
//    @Override
//    public Page<AboutBoard> findAllAboutBoardList(Pageable pageable) {
//        return aboutBoardRepository.findAll(pageable);
//    }
//}

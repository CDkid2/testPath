package com.pp.impl;

import com.pp.domain.Des;
import com.pp.repository.DesRepository;
import com.pp.service.DesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesServiceImpl implements DesService {

    @Autowired
    private DesRepository desRepository;

    @Override
    public List<Des> findByDesNameContaining(String keyword) {
        return desRepository.findByDesNameContaining(keyword);
    }

    public List<Des> findByDClsCodeWithDesName(String desName) {
        return desRepository.findByDClsCodeWithDesName(desName);
    }

//    public List<Des> findDesCodeByDesNameContaining(String keyword) {
//        return desRepository.findDesCodeByDesNameContaining(keyword);
//    }
    public List<Des> findByDesCls_dClsCode(String dClsCode) {
        // DesRepository에서 findByDesCls_DClsCode 메소드를 구현해야 합니다.
        return desRepository.findByDesClsCode_dClsCode(dClsCode);
    }


    public List<Des> autocomplete(String keyword) {
        return desRepository.findByDesNameLike(keyword);
    }

    @Override
    public List<Des> getTop3Des() {
        // 이 메서드의 구현을 작성해주세요.
        return desRepository.findTop3ByOrderByDesLikeDesc();
    }

    @Override
    public List<Des> getRandom4Des() {
        List<Des> desList = desRepository.findAll();
        Collections.shuffle(desList);
        return desList.stream().limit(4).collect(Collectors.toList());
    }

}
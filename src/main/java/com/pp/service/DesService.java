package com.pp.service;

import com.pp.domain.Des;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DesService {

//    List<Des> findDesCodeByDesNameContaining(String keyword);

    List<Des> findByDesNameContaining(String keyword);
    List<Des> findByDClsCodeWithDesName(String desName);

    List<Des> findByDesCls_dClsCode(String dClsCode);

    List<Des> autocomplete(String keyword);

    List<Des> getTop3Des();  // desLike가 높은 순으로 3개의 Des 객체를 반환

    List<Des> getRandom4Des();

}

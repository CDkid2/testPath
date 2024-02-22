package com.pp.controller;

import com.pp.domain.Des;
import com.pp.domain.Descls;
import com.pp.service.DesService;
import com.pp.service.ImageService;
import com.pp.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/destinations")

public class DesController {
    private static final Logger logger = LoggerFactory.getLogger(DesController.class);

    private final DesService desService;
    private final ImageService imageService;
    private final ReviewService reviewService;

    @Autowired
    public DesController(DesService desService, ImageService imageService, ReviewService reviewService) {
        this.desService = desService;
        this.imageService = imageService;
        this.reviewService = reviewService;
    }
    @GetMapping("/detail")
    public String detailDestinations(@RequestParam(name="keyword", required=false) String keyword, Model model) {
        logger.info("Received search keyword: {}", keyword);

        // 키워드에 해당하는 목적지 검색
        List<Des> destinations = desService.findByDesNameContaining(keyword);

        // 검색결과의 dcls와 같은 dcls를 가진 목적지들을 추가로 검색
        if(!destinations.isEmpty()) {
            String desName = destinations.get(0).getDesName();
            List<Des> samClsDestinations = desService.findByDClsCodeWithDesName(desName);
            destinations.addAll(samClsDestinations);
        }
        model.addAttribute("destinations", destinations);
        return "detail/detail1";
    }

    @GetMapping("/search")
    public String searchDestinations(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        logger.info("Received search keyword: {}", keyword);

        // 키워드에 해당하는 목적지 검색
        List<Des> destinations = desService.findByDesNameContaining(keyword);
        logger.info("Search results: {}", destinations.get(0).getDesName());


        // 검색 결과의 dcls와 같은 dcls를 가진 목적지들을 추가로 검색
        if (!destinations.isEmpty()) {
            String desName = destinations.get(0).getDesName();
            List<Des> sameClsDestinations = desService.findByDClsCodeWithDesName(desName);
            destinations.addAll(sameClsDestinations);

            // 중복 제거
            List<Des> distinctDestinations = destinations.stream()
                    .distinct()
                    .collect(Collectors.toList());
            destinations = distinctDestinations;

            logger.info("dcls results: {}", distinctDestinations );
        }

        if (!destinations.isEmpty()) {
            String dClsCode = destinations.get(0).getDesClsCode().getDClsCode();
            model.addAttribute("dClsCode", dClsCode);
        }
        model.addAttribute("destinations", destinations);
        return "main/mainMap";  // Thymeleaf 템플릿 이름

    }
    @GetMapping("/autocomplete")
    @ResponseBody
    public List<Des> autocomplete(@RequestParam String keyword) {
        return desService.autocomplete(keyword);
    }

}

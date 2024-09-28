package com.example.certificateinfo.controller;

import com.example.certificateinfo.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CertificateDetailController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateDetailController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/certificates/{name}")
    public String getCertificateDetail(@PathVariable String name, Model model) {
        // 자격증 이름에 맞는 jmCd 값을 찾기 위한 매핑
        Map<String, String> certificateCodeMap = new HashMap<>();
        certificateCodeMap.put("정보관리기술사", "0601");
        certificateCodeMap.put("컴퓨터시스템응용기술사", "0622");
        certificateCodeMap.put("정보처리기사", "1320");
        certificateCodeMap.put("전자계산기조직응용기사", "1322");
        certificateCodeMap.put("전자계산기기사", "1321");
        certificateCodeMap.put("로봇소프트웨어개발기사", "1178");
        certificateCodeMap.put("임베디드기사", "1121");
        certificateCodeMap.put("정보처리산업기사", "2290");
        certificateCodeMap.put("사무자동화산업기사", "2193");
        certificateCodeMap.put("컴퓨터응용가공산업기사", "2025");
        certificateCodeMap.put("전자계산기제어산업기사", "2324");
        certificateCodeMap.put("정보처리기능사", "6921");
        certificateCodeMap.put("정보기기운용기능사", "6892");
        certificateCodeMap.put("전자계산기기능사", "6801");
        certificateCodeMap.put("컴퓨터응용선반기능사", "6012");
        certificateCodeMap.put("컴퓨터응용밀링기능사", "6032");
        certificateCodeMap.put("전자캐드기능사", "6785");
        certificateCodeMap.put("컴퓨터그래픽스운용기능사", "7796");
        certificateCodeMap.put("웹디자인기능사", "7798");
        certificateCodeMap.put("전자출판기능사", "6791");

        String jmCd = certificateCodeMap.get(name);
        if (jmCd != null) {
            // API를 통해 자격증 상세 정보를 가져옴
            Map<String, Object> certificateInfo = certificateService.getCertificateInfo(jmCd);
            model.addAttribute("certificateInfo", certificateInfo);
        } else {
            model.addAttribute("error", "해당 자격증의 정보를 찾을 수 없습니다.");
        }

        model.addAttribute("certificateName", name);
        return "certificateDetail";
    }
}
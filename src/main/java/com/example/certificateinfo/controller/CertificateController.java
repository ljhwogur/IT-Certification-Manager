package com.example.certificateinfo.controller;

import com.example.certificateinfo.model.Certificate;
import com.example.certificateinfo.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    // 전체 자격증 목록을 반환하는 엔드포인트
    @GetMapping
    public List<Certificate> getAllCertificates() {
        return certificateService.getAllCertificates();
    }

    // 특정 자격증의 상세 정보를 JSON 형식으로 반환하는 엔드포인트
    @GetMapping("/{name}")
    public Map<String, Object> getCertificateDetail(@PathVariable String name) {
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

        String jmCd = certificateCodeMap.get(name);
        if (jmCd != null) {
            return certificateService.getCertificateInfo(jmCd);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "해당 자격증의 정보를 찾을 수 없습니다.");
            return errorResponse;
        }
    }
}

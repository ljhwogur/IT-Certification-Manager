package com.example.certificateinfo.controller;


import com.example.certificateinfo.model.Certificate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class CertificateController {

    @GetMapping("/certificates")
    public String getCertificates(Model model) {
        List<Certificate> certificates = Arrays.asList(
                new Certificate("정보관리기술사", ""),
                new Certificate("컴퓨터시스템응용기술사", ""),
                new Certificate("정보처리기사", ""),
                new Certificate("전자계산기조직응용기사", ""),
                new Certificate("전자계산기기사", ""),
                new Certificate("로봇소프트웨어개발기사", ""),
                new Certificate("임베디드기사", ""),
                new Certificate("정보처리산업기사", ""),
                new Certificate("사무자동화산업기사", ""),
                new Certificate("컴퓨터응용가공산업기사", ""),
                new Certificate("전자계산기제어산업기사", ""),
                new Certificate("정보처리기능사", ""),
                new Certificate("정보기기운용기능사", ""),
                new Certificate("전자계산기기능사", ""),
                new Certificate("컴퓨터응용선반기능사", ""),
                new Certificate("컴퓨터응용밀링기능사", ""),
                new Certificate("전자캐드기능사", ""),
                new Certificate("컴퓨터그래픽스운용기능사", ""),
                new Certificate("웹디자인기능사", ""),
                new Certificate("전자출판기능사", "")
        );

        model.addAttribute("certificates", certificates);
        return "certificates";
    }
}

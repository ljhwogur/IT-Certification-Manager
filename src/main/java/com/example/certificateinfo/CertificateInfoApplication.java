package com.example.certificateinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.certificateinfo")
public class CertificateInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CertificateInfoApplication.class, args);
    }
}

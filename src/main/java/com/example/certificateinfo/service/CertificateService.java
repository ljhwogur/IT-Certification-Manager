package com.example.certificateinfo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.example.certificateinfo.model.Certificate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CertificateService {

    private final RestTemplate restTemplate;
    private final XmlMapper xmlMapper;

    @Value("${certificate.api.service.url}")
    private String openApiUrl;

    @Value("${certificate.api.service.key}")
    private String apiKey;

    // 자격증 데이터를 담을 리스트
    private final List<Certificate> certificates = new ArrayList<>();

    public CertificateService() {
        this.restTemplate = new RestTemplate();
        this.xmlMapper = new XmlMapper();
    }

    // 전체 자격증 목록을 반환
    public List<Certificate> getAllCertificates() {
        return certificates;  // 기본적으로 로컬에 저장된 자격증 목록 반환
    }

    // 특정 자격증 정보를 XML에서 JSON으로 변환하여 반환
    public Map<String, Object> getCertificateInfo(String jmCd) {
        String url = openApiUrl + "?serviceKey=" + apiKey + "&jmCd=" + jmCd;

        try {
            URI uri = new URI(url);
            // XML 응답을 문자열로 받아옴
            String xmlResponse = restTemplate.getForObject(uri, String.class);

            // XML 응답을 JSON 형식의 Map으로 변환
            Map<String, Object> jsonMap = xmlMapper.readValue(xmlResponse, Map.class);

            // 변환된 데이터를 가공하여 반환
            Map<String, Object> result = new HashMap<>();
            if (jsonMap != null && jsonMap.get("body") != null) {
                Map<String, Object> body = (Map<String, Object>) jsonMap.get("body");
                Map<String, Object> items = (Map<String, Object>) body.get("items");

                // JSON 형식으로 변환된 자격증 정보를 반환
                result.put("items", items.get("item"));
            }

            return result;

        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}

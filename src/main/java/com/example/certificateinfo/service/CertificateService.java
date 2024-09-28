package com.example.certificateinfo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CertificateService {

    private final RestTemplate restTemplate;
    private final XmlMapper xmlMapper;

    public CertificateService() {
        this.restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        this.xmlMapper = new XmlMapper();
    }

    public Map<String, Object> getCertificateInfo(String jmCd) {
        String apiUrl = "http://openapi.q-net.or.kr/api/service/rest/InquiryTestInformationNTQSVC/getJMList";
        String serviceKey = "tL3ANXSExCueWpgNwnqjijmxBTpyhnycYQw1wj11ja9uo/5HveCA3oyyOw13qjnab0uIcZqGt9/m70N1ZEiUtQ==";
        String url = apiUrl + "?serviceKey=" + serviceKey + "&jmCd=" + jmCd;

        try {
            URI uri = new URI(url);
            String xmlResponse = restTemplate.getForObject(uri, String.class);

            // XML 응답 확인을 위해 출력
            System.out.println("XML Response: " + xmlResponse);

            // JSON 변환
            Map<String, Object> jsonMap = xmlMapper.readValue(xmlResponse, Map.class);

            // 필요한 데이터만 추출하여 반환
            Map<String, Object> result = new HashMap<>();
            if (jsonMap != null && jsonMap.get("body") != null) {
                Map<String, Object> body = (Map<String, Object>) jsonMap.get("body");
                Map<String, Object> items = (Map<String, Object>) body.get("items");
                List<Map<String, String>> itemList = (List<Map<String, String>>) items.get("item");

                // 필요한 데이터만 추출
                List<Map<String, String>> formattedItems = new ArrayList<>();
                for (Map<String, String> item : itemList) {
                    Map<String, String> formattedItem = new HashMap<>();
                    formattedItem.put("implplannm", item.get("implplannm"));
                    formattedItem.put("docregstartdt", item.get("docregstartdt"));
                    formattedItem.put("docregenddt", item.get("docregenddt"));
                    formattedItem.put("docexamstartdt", item.get("docexamstartdt"));
                    formattedItem.put("docexamenddt", item.get("docexamenddt"));
                    formattedItem.put("docpassdt", item.get("docpassdt"));
                    formattedItem.put("pracregstartdt", item.get("pracregstartdt"));
                    formattedItem.put("pracregenddt", item.get("pracregenddt"));
                    formattedItem.put("pracexamstartdt", item.get("pracexamstartdt"));
                    formattedItem.put("pracexamenddt", item.get("pracexamenddt"));
                    formattedItem.put("pracpassstartdt", item.get("pracpassstartdt"));
                    formattedItem.put("pracpassenddt", item.get("pracpassenddt"));
                    formattedItems.add(formattedItem);
                }

                result.put("items", formattedItems);
            }

            System.out.println("Formatted JSON: " + result);
            return result;

        } catch (URISyntaxException e) {
            System.err.println("Invalid URI syntax: " + e.getMessage());
            e.printStackTrace();
            return new HashMap<>();
        } catch (JsonProcessingException e) {
            System.err.println("Error processing XML to JSON: " + e.getMessage());
            e.printStackTrace();
            return new HashMap<>();
        } catch (Exception e) {
            System.err.println("An error occurred while fetching certificate info: " + e.getMessage());
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
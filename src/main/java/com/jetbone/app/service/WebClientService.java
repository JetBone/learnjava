package com.jetbone.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Chris
 * @date 2021-06-19
 */
@Service
@RequiredArgsConstructor
public class WebClientService {

    private final RestTemplate restTemplate = new RestTemplate();


    public void testRestTemplate() {
    }
}

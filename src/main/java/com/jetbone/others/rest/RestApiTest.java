package com.jetbone.others.rest;

import com.jetbone.app.bean.ApiResult;
import com.jetbone.app.controller.param.PostParam;
import io.swagger.models.Response;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Chris
 * @date 2021-06-22
 */
public class RestApiTest {

    private static final String GET_URI = "http://localhost:8080/hello/get";
    private static final String POST_URI = "http://localhost:8080/hello/post";

    public static void main(String[] args) throws Exception {

//        restTemplateTest();

        WebClient webClient = WebClient.create();
        for (int i = 0; i < 10; i++) {
            webClientTest(webClient, GET_URI);
        }
        Thread.sleep(60000);
    }


    private static void webClientTest(WebClient webClient, String url) throws Exception {

        Mono<ApiResult> result = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ApiResult.class);

        result.subscribe(t -> System.out.println("####### Thread: - " + Thread.currentThread().getName() + " - result: " + t.toString()));

    }

    private static void restTemplateTest() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        PostParam body = new PostParam();
        body.setCode("007");
        body.setValue("post");

        HttpEntity<PostParam> entity = new HttpEntity<>(body, headers);



        var responseEntity = restTemplate.exchange(POST_URI, HttpMethod.POST, entity, ApiResult.class);
        System.out.println(responseEntity);
    }
}

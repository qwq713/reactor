package org.example;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;

public class Example6_3 {
    public static void main(String[] args) {

        // https://heekim0719.tistory.com/425
        // Non-Blocking I/O 방식의 통신은 아님.
        // Reactor 를 사용하여 Request & Response 를 하나의 Operator 체인으로 깔끔하게 처리.

        URI worldTimeUri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Mono.just(restTemplate.exchange(worldTimeUri,
                                        HttpMethod.GET,
                                        new HttpEntity<String>(headers),
                                        String.class)
                )
                .map(response -> {
                    DocumentContext jsonContext =
                            JsonPath.parse(response.getBody());
                    return jsonContext.read("$.datetime");
                })
                .subscribe(
                        // Consumer - Operator 에서 가공한 데이터
                        data -> System.out.println("# emitted data: " + data),

                        // ErrorConsumer
                        System.out::println,

                        // onCompleteConsumer
                        () -> System.out.println("# emitted onComplete signal")
                );
    }
}

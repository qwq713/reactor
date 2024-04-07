package org.example;

import reactor.core.publisher.Flux;

public class Example5_1 {
    public static void main(String[] args) {
        // Flux[N] : Publisher : 입력으로 들어오는 데이터를 제공
        // Hello , Reactor : Publisher가 최초로 제공하는 가공하지 않은 데이터 -> 데이터 소스
        Flux<String> sequence = Flux.just("Hello", "Reactor"); // just . map  : Operator
        sequence.map(String::toLowerCase)
                // data -> System.out.println(data) : Subscriber . Consumer
                .subscribe(System.out::println);
    }
}

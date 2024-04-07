package org.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example6_4 {
    public static void main(String[] args) {
        Flux.just(6, 9, 13)
                .map(num -> num % 2)
                .subscribe(System.out::println);

        // 배열 데이터 처리를 위해 just 대신 fromArray 사용.
        Flux.fromArray(new Integer[]{3, 6, 7, 9})
                .filter(num -> num > 6)
                .map(num -> num * 2)
                .subscribe(System.out::println);

        // concatWith : 두개의 Publisher(Mono) 가 각각 emit 하는 데이터들을 하나로 연결해서 새로운 Publisher(Flux) 의 데이터 소스로 만들어 주는 Operator
        Flux<String> flux = Mono.justOrEmpty("steve")
                .concatWith(Mono.justOrEmpty("jobs"));
        flux.subscribe(System.out::println);

        // concatWitch 대신 concat 사용. concatWith 는 2개의 데이터소스만 연결 가능
        Flux.concat(
                        Flux.just("Mercury", "Venus", "Earth"),
                        Flux.just("Mars", "Jupiter", "Saturn"),
                        Flux.just("Uranus", "Neptune", "Pluto"))
                .collectList()
                .subscribe(System.out::println);
    }
}

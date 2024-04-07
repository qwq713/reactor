package org.example;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

public class Example7_1 {
    public static void main(String[] args) throws InterruptedException {
        // Cold Sequence
        Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("KOREA", "JAPAN", "CHINESE"))
                .map(String::toLowerCase);
        coldFlux.subscribe(country -> System.out.println("# Subscriber1: " + country));
        System.out.println("-----------------------");
        Thread.sleep(2000L);
        // 구독이 발생할 때마다 데이터를 처음부터 다시 전달받고 있음.
        coldFlux.subscribe(country -> System.out.println("# Subscriber2: " + country));

        // Hot Sequence
        String[] singers = {"A", "B", "C", "D", "E"};
        System.out.println("# Begin concert:");
        Flux<String> concertFlux = Flux.fromArray(singers)
                // 1초에 한명씩 공연.
                .delayElements(Duration.ofSeconds(1))
                .share(); // Hot Sequence 로 동작하게 설정.

        concertFlux.subscribe(
                singer -> System.out.println("# Subscriber1 is watching " + singer + "'s song")
        );
        Thread.sleep(2500);

        // 2.5초후 두번째 관중이 공연 시작.
        concertFlux.subscribe(
                singer -> System.out.println("# Subscriber2 is watching " + singer + "'s song")
        );
        Thread.sleep(3000);
    }
}

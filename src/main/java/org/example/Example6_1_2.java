package org.example;

import reactor.core.publisher.Mono;

public class Example6_1_2 {
    public static void main(String[] args) {
        Mono.just("Hello Reactor")
                .subscribe(System.out::println);

        // 데이터를 한 건도 emit 하지 않는 예제 코드.
        Mono
                .empty() // empty Operator 사용 시 데이터를 emit 하지 않고 onComplete Signal 을 전송
                .subscribe(
                        // consumer
                        none -> System.out.println("# emitted onNext signal"),
                        // errorConsumer
                        error -> {
                        },
                        // completeConsumer
                        () -> System.out.println("# emitted onComplete signal")
                );
        // 출력 결과 : # emitted onComplete signal
        // Mono.empty() : 데이터를 전달받을 필요는 없지만 작업이 끝났음을 알리고 이에 따른 후처리를 하고 싶을 때 사용.
    }
}

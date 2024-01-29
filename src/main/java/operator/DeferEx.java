package operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class DeferEx {
    public static void main(String[] args) throws InterruptedException {
        log.info("#start: {}", LocalDateTime.now());
        // 선언 시점에 실행
        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        // subscribe 시점에 실행
        Mono<LocalDateTime> deferMono = Mono.defer(()->Mono.just(LocalDateTime.now()));

        Thread.sleep(2000L);

        justMono.subscribe(d->log.info("#justMono: {}",d));
        // defer 실행
        deferMono.subscribe(d->log.info("#deferMono: {}",d));

        Thread.sleep(2000L);

        justMono.subscribe(d->log.info("#justMono: {}",d));
        deferMono.subscribe(d->log.info("#deferMono: {}",d));

        log.info("--------------------------");

        log.info("#start: {}",LocalDateTime.now());
        Mono.just("Hi")
                .delayElement(Duration.ofSeconds(3))
                .switchIfEmpty(Mono.defer(DeferEx::sayHello))
                .subscribe(d->log.info("#data: {}",d));

        Thread.sleep(3000L);
    }

    static Mono<String> sayHello(){
        log.info("#sayHello");
        return Mono.just("Hello");
    }
}

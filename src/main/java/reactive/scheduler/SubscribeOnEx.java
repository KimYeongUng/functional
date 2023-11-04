package reactive.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SubscribeOnEx {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new String[]{"1","2","3","4","5"})
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(d->log.info("#doOnNext: {}",d))
                .doOnSubscribe(subscription -> log.info("#doOnSubscribe"))
                .subscribe(d->log.info("#onNext: {}",d));

        Thread.sleep(500L);

    }
}

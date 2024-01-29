package scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class PubSubOnEx {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1,3,5,7,9})
                .doOnNext(d->log.info("#doOnNext: {}",d))
                .subscribeOn(Schedulers.boundedElastic())
                .filter(d->d>3)
                .doOnNext(d->log.info("#filter: {}",d))
                .publishOn(Schedulers.parallel())
                .map(d->d*10)
                .doOnNext(d->log.info("#map: {}",d))
                .subscribe(d->log.info("#d: {}",d));

        Thread.sleep(500L);
    }
}

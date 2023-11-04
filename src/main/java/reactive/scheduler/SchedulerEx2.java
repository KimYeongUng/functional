package reactive.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SchedulerEx2 {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1,10)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(d->log.info("#doOnNext: {}",d))
                .filter(x->x%2 == 0)
                .doOnNext(d->log.info("#filter: {}",d))
                .publishOn(Schedulers.newSingle("pubOn"))
                .map(d->d*10)
                .doOnNext(d->log.info("#map: {}",d))
                .subscribe(d->log.info("#data: {}",d));

        System.out.println("EXIT");
        Thread.sleep(500L);
    }
}

package scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SchedulerEx01 {
    public static void main(String[] args) throws InterruptedException{
        Flux.fromArray(new Integer[]{1,3,4,5,7,9})
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(s->log.info("#doOnNext: {}",s))
                .doOnSubscribe(sub->log.info("#doOnSubscribe"))
                .publishOn(Schedulers.parallel()) // change downstream thread
                .subscribe(d->log.info("#data: {}",d));

        Thread.sleep(500L);
    }
}

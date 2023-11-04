package reactive.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class PublishOnEx {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1,2,3,4,5})
                .doOnNext(d->log.info("#doOnNext: {} ",d))
                .doOnSubscribe(d->log.info("#doOnSubscribe"))
                .publishOn(Schedulers.parallel())
                .subscribe(d->log.info("# onNext: {}",d));

        Thread.sleep(500L);
    }
}

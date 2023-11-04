package reactive.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class pararellEx {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1,20)
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(d->log.info("#onNext: {}",d));

        Thread.sleep(1000L);
    }
}
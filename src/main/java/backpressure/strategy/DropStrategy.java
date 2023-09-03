package backpressure.strategy;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class DropStrategy {
    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(5L))
                .onBackpressureDrop(dropped->log.info("#Dropped: {}",dropped))
                .publishOn(Schedulers.parallel())
                .subscribe(data->{
                    try {
                        Thread.sleep(5L);
                    }catch (InterruptedException e){}

                    log.info("#OnNext(): {}",data);
                }, error->log.info("#onError: {}",error)
                );
        Thread.sleep(2000L);
    }
}

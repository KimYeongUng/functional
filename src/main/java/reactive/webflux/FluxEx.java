package reactive.webflux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FluxEx {
    public static void main(String[] args) throws InterruptedException {

        Flux.interval(Duration.ofMillis(500))
                .take(10)
                .subscribe(d->log.debug("#onNext:{}",d));

        log.debug("END");
        TimeUnit.SECONDS.sleep(10);
    }
}
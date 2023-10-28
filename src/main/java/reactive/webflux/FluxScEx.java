package reactive.webflux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FluxScEx {
    public static void main(String[] args) throws InterruptedException {

        //JVM - User Thread가 남아있으면 종료하지 않음
        Flux.interval(Duration.ofMillis(500))
                .take(12)
                .filter(x->x%2 == 0)
                .subscribe(s->log.debug("#onNext: {}",s));

        TimeUnit.SECONDS.sleep(10);
        log.debug("EXIT");
    }
}

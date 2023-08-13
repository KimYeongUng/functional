package flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class HotSequence {
    public static void main(String[] args) throws InterruptedException {
        String[] data = {"a","b","c","d","e"};
        log.info("#emit");
        Flux<String> hotseq =
                Flux.fromArray(data)
                        .delayElements(Duration.ofSeconds(1))
                        .share();

        hotseq.subscribe(d->log.info("sub #1 {}",d));

        Thread.sleep(2500);

        hotseq.subscribe(d->log.info("sub #2 {}",d));

        Thread.sleep(3000);
    }
}
package flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Slf4j
public class ColdSequence {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> coldSeq = Flux.fromIterable(Arrays.asList(1,2,3,4,5))
                .map(String::valueOf);

        // first subscribe
        coldSeq.subscribe(d->log.info("sub #1: {} ",d));

        System.out.println();
        Thread.sleep(2000L);

        // second subscribe
        coldSeq.subscribe(d->log.info("sub #2: {}",d));


    }
}

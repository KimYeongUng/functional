package scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class pararellEx {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1,3,5,7,9,11,12,44,55,122,143,332,653})
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(d->log.info("#data: {}",d));

        Thread.sleep(500L);
    }
}

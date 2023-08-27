package backpressure;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class BPEx01 {
    public static void main(String[] args) {
        Flux.range(1,5)
                .doOnRequest(d->log.info("#doOnRequest: {}",d))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @SneakyThrows
                    @Override
                    protected void hookOnNext(Integer value) {
                        Thread.sleep(2000L); // delay
                        log.info("#hookOnNext: {}", value);
                        request(1);
                    }
                });
    }
}

package sink;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;


@Slf4j
public class SinkEx02 {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;

        Sinks.Many<String> unicastSink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<String> fluxview = unicastSink.asFlux();

        IntStream.range(1,tasks).forEach(
                n->{
                    try {
                        new Thread(()->{
                            unicastSink.emitNext(doTask(n),Sinks.EmitFailureHandler.FAIL_FAST);
                            log.info("# emitted: {}",n);
                        }).start();
                        Thread.sleep(100L);
                    }catch (InterruptedException e){
                        log.error(e.getMessage());
                    }
                }
        );

        fluxview.publishOn(Schedulers.parallel())
                .map(res->res+" success")
                .doOnNext(n->log.info("#map(): {}",n))
                .publishOn(Schedulers.parallel())
                .subscribe(data->log.info("#onNext(): {}",data));

        Thread.sleep(200L);
    }

    private static String doTask(int n){
        return "result"+n;
    }
}

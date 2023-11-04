package sink;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

@Slf4j
public class SinkEx01 {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;
        Flux.create((FluxSink<String> sink)->{
            IntStream
                    .range(1,tasks)
                    .forEach(n->sink.next(doTask(n)));
        })
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(d->log.info("#create: {}",d))
                .log()
                .publishOn(Schedulers.parallel())
                .map(res->res+" Success")
                .log()
                .doOnNext(d->log.info("#map: {}",d))
                .publishOn(Schedulers.parallel())
                .subscribe(d->log.info("#onNext: {}",d));
    }

    private static String doTask(int d) {
        return "task "+d+" result";
    }

}
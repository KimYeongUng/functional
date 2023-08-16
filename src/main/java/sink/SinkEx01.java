package sink;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

@Slf4j
public class SinkEx01 {
    public static void main(String[] args) {
        int tasks = 6;

        Flux.create((FluxSink<String> sinks)-> IntStream.range(1,tasks).forEach(n->sinks.next(doTask(n))))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(n->log.info("# create(): {}",n))
                .publishOn(Schedulers.parallel())
                .map(result->result+" success")
                .doOnNext(n->log.info("map(): {}",n))
                .publishOn(Schedulers.parallel())
                .subscribe(d->log.info("onNext(): {}",d));
    }

    private static String doTask(int n) {
        return "task"+n+" result";
    }
}

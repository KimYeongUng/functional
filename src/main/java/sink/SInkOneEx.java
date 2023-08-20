package sink;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

@Slf4j
public class SInkOneEx {
    public static void main(String[] args) {
        Sinks.One<String> sinkOne = Sinks.one();
        Mono<String> mono = sinkOne.asMono();
        sinkOne.emitValue("Data1 emit",FAIL_FAST);
        sinkOne.emitValue("Data2 emit",FAIL_FAST); // dropped
        mono.subscribe(data->log.info("# Subscriber1 {}",data));
        mono.subscribe(data->log.info("# Subscriber2 {}",data));
    }
}

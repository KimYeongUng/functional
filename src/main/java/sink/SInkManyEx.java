package sink;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;
@Slf4j
public class SInkManyEx {
    public static void main(String[] args) {
        Sinks.Many<Integer> unicastsink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> fluxview = unicastsink.asFlux();

        unicastsink.emitNext(1,FAIL_FAST);
        unicastsink.emitNext(2,FAIL_FAST);

        fluxview.subscribe(data->log.info("#Subscriber1 : {}",data));
        unicastsink.emitNext(3,FAIL_FAST);

        // IllegalStateException - allows only a single Subscriber (unicast)
        // fluxview.subscribe(data->log.info("#Subscriber1 : {}",data));

    }
}

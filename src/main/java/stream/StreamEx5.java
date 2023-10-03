package stream;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class StreamEx5 {
    public static void main(String[] args) {
        Flux
                .just(1,2,3,4,5,6)
                .filter(x->x%2==0)
                .map(x->x*2)
                .subscribe(d->log.info("#{}",d));
    }
}

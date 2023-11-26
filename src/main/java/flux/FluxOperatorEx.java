package flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;

import java.util.List;

@Slf4j
public class FluxOperatorEx {
    public static void main(String[] args) throws InterruptedException {
        log.info("delayElement");
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        Flux.fromIterable(list)
                .log()
                .delayElements(Duration.ofMillis(10))
                .subscribe(System.out::println);

        Thread.sleep(500L);
    }
}

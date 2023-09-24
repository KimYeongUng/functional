package publisher;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class PublisherEx01 {
    public static void main(String[] args) {
        List<Integer> elem = new ArrayList<>();
        Flux.range(1,5).subscribe(elem::add);
        elem.forEach(System.out::println);
    }
}

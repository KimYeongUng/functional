package flux;

import reactor.core.publisher.Flux;

public class FluxEx1 {
    public static void main(String[] args) {
        Flux.just(1,2,3,4,5)
                .filter(i->i>1)
                .map(i->i*10)
                .map(i->i+" ")
                .subscribe(System.out::print);
        System.out.println();
        Flux<String> seq =  Flux.just("Hello","World");
        seq.map(String::toUpperCase)
                .subscribe(d-> System.out.print(d+" "));

    }
}
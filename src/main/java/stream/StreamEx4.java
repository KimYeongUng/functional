package stream;

import java.util.stream.Stream;
public class StreamEx4 {
    public static void main(String[] args) {
        // method chaining : distinct , filter
        // terminal operation : forEach
        Stream.of(1,2,3,4,"hi",1,2,"Trust Me When I Say~")
                .distinct()
                .filter(num->num instanceof String)
                .forEach(val -> System.out.print(val+" "));
    }
}
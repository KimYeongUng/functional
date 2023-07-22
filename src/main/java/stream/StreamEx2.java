package stream;

import java.util.stream.IntStream;

public class StreamEx2 {
    public static void main(String[] args) {
        // Stream - Integer
        // rangeClosed
        IntStream.rangeClosed(1,10).forEach(num-> System.out.print(num+" "));

        System.out.println();

        // iterate infinity -> limit 10
        IntStream.iterate(1,i->i+1).limit(10).forEach(num->System.out.print(num+" "));
    }
}
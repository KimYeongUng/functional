package functional.function_descriptor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerEx {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        // Consumer: T -> void
        consume(list,num->System.out.print("#"+num+" "));
    }

    private static <T> void consume(List<T> list, Consumer<T> consumer) {
        for (T t:list)
            consumer.accept(t);
    }
}

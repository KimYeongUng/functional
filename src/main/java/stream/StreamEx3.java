package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamEx3 {
    public static void main(String[] args) {
        Stream.of(1,2,3,4,5).forEach(i-> System.out.print(i+" "));
        System.out.println();
        final List<Integer> nums = Arrays.asList(1,2,3,4,5);
        nums.forEach(i-> System.out.print(i+" "));
        System.out.println();
        final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Optional<Integer> find = numbers.stream()
                .filter(i->i>=3&&i<=9)
                .map(num->num*2)
                .findFirst();

        System.out.println(find);
    }

}

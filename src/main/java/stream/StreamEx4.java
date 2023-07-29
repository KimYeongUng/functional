package stream;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
public class StreamEx4 {
    public static void main(String[] args) {
        // method chaining : distinct , filter
        // terminal operation : forEach
        Stream.of(1,2,3,4,"hi",1,2,"Trust Me When I Say~")
                .distinct()
                .filter(num->num instanceof String)
                .forEach(val -> System.out.print(val+" "));

        System.out.println("OR");

        List<String> list = Stream.of(1,3,3,5,7,2,1,3)
                .filter(i->i>2)
                .map(i->i*2)
                .map(i->"#"+i)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);

        final Integer integer = 3;
        final Integer integer128 = 128;

        // == -> Integer.valueOf(i)로 비교 , 메모리 레퍼런스 체크 아님
        Optional<Integer> find = Stream.of(1,2,3,4,5)
                // .filter(i-> i == integer) // working
                .filter(i->i.equals(integer))
                .findFirst();
        System.out.println(find);

        // not cached , not equal memory reference , use equals()
        Optional<Integer> find2 = Stream.of(1,3,5,128)
                //.filter(i->i == integer128) // not working
                .filter(i->i.equals(integer128))
                .findFirst();
        System.out.println(find2);
    }
}
package functional.function_descriptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SupplierEx {
    public static void main(String[] args) {
        String res = createData();
        System.out.println(res);
    }

    private static String createData() {
        return Stream.generate(SupplierEx::getData).limit(3).collect(Collectors.joining(" "));
    }

    private static String getData(){
        List<String> list = Arrays.asList("This","is","List","of","Strings");
        Collections.shuffle(list);
        return list.get(0);
    }
}

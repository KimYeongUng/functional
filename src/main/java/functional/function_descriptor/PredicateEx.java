package functional.function_descriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateEx {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4,5);

        // Predicate : T -> boolean
        List<Integer> res = filter(list,num->num>2);
        System.out.println(res);
    }

    static <T> List<T> filter(List<T> list, Predicate<T> predicate){
        List<T> res = new ArrayList<>();
        for (T t:list){
            if(predicate.test(t))
                res.add(t);
        }
        return res;
    }
}

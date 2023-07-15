package functional.identity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.List;

public class IdentityEx {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,2,3,4,5);
        System.out.println(
                map2(nums,i->i*2)
        );
        System.out.println(
                map(nums,i->i*2)
        );

        System.out.println(
                map2(nums,null)
        );

        System.out.println(
                map(nums,null)
                // or
                // map(nums,i->i);
        );

        System.out.println(
                map(nums,Function.identity())
        );


    }

    private static <T,R> List<R> map2(List<T> list,Function<T,R> mapper){
        Function<T,R> function;

        if(mapper != null)
            function = mapper;
        else
            function = x->(R)x; // identity function

        final List<R> ret = new ArrayList<>();

        for (T t:list)
            ret.add(function.apply(t));

        return ret;
    }

    private static <T,R> List<R> map(List<T> list, Function<T,R> mapper){

        final List<R> ret = new ArrayList<>();

        for (T t:list) {

            if(mapper!= null)
                ret.add(mapper.apply(t));
            else
                ret.add((R)t);
        }
        return ret;
    }
}

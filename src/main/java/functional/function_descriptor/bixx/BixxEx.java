package functional.function_descriptor.bixx;

import java.util.function.BiFunction;

public class BixxEx {
    public static void main(String[] args) {
        String bifunc = getBiFunction("#","1",(t,u)->t+ u);
        System.out.println(bifunc);
    }

    private static <T,U,R> R getBiFunction(T t,U u,BiFunction<T,U,R> biFunction){
        return biFunction.apply(t,u);
    }
}

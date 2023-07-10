package functional_interface;

import java.math.BigDecimal;

public class FunctionalEx {
    public static void main(String[] args) {
        doSomething("value: ",30,20,(msg,i1,i2)->msg+(i1*i2));
        doSomething(1,2,3,(n1,n2,n3)->String.valueOf(n1+n2+n3));

        BigDecimalToCurrency bigDecimalToCurrency = bd->"$"+bd;

        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal(100000000)));
    }

    private static <T1,T2,T3> void doSomething(T1 t1,T2 t2,T3 t3,TripleFunction<T1,T2,T3,String> function){
        System.out.println(function.apply(t1,t2,t3));
    }
}

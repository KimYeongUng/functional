package functional;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalEx2 {
    public static void main(String[] args) {

        List<Product> productList = Arrays.asList(
                new Product(1L,"P1",new BigDecimal("10.00")),
                new Product(2L,"P2",new BigDecimal("110.00")),
                new Product(3L,"P3",new BigDecimal("130.00")),
                new Product(4L,"P4",new BigDecimal("150.00")),
                new Product(5L,"P5",new BigDecimal("120.00"))
        );

        final BigDecimal twenty = new BigDecimal("20.00");

        System.out.println("Ordinary");

        // for loop
        List<Product> filterer = filter(productList,p->p.getPrice().compareTo(twenty)>=0);
        System.out.println("filter: "+filterer);

        List<BigDecimal> bigDecimals = map(filterer, Product::getPrice);
        final BigDecimal total = total(productList, Product::getPrice);

        System.out.println("total price: $"+total);
        final List<Product> expensives = filter(productList,p->p.getPrice().compareTo(new BigDecimal("50"))>0);
        final List<Product> discounted = map(expensives,p->new DiscountedProduct(p.getId(),p.getName(),p.getPrice()
                .multiply(new BigDecimal("0.5"))));


        System.out.println("discounted: "+discounted);

        System.out.println("Stream");
        // stream
        List<Product> stream = productList.stream().filter(p->p.getPrice().compareTo(twenty)>=0)
                .collect(Collectors.toList());
        System.out.println(stream);
        System.out.println("stream Total Price: ");

    }

    private static <T> List<T> filter(List<T> list , Predicate<? super T> predicate){
        List<T> ret = new ArrayList<>();
        for (T t:list){
            if(predicate.test(t))
                ret.add(t);
        }
        return ret;
    }

    private static <T> BigDecimal total(List<T> list,Function<T,BigDecimal> function){
        BigDecimal total = BigDecimal.ZERO;
        for (final  T t:list){
            total = total.add(function.apply(t));
        }

        return total;
    }

    private static <T,R> List<R> map(List<T> list, Function<T,R> function){
        List<R> ret = new ArrayList<>();

        for (T t:list){
            ret.add(function.apply(t));
        }

        return ret;
    }
}

@ToString(callSuper = true)
class DiscountedProduct extends Product{
    DiscountedProduct(Long id,String name,BigDecimal price){
        super(id,name,price);
    }
}
package stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import functional.Product;

public class StreamApiEx {
    public static void main(String[] args) {
        Product product1 = new Product(1L,"product1",new BigDecimal("100.00"));
        Product product2 = new Product(2L,"product2",new BigDecimal("20.00"));
        Product product3 = new Product(3L,"product2",new BigDecimal("120.00"));
        Product product4 = new Product(4L,"product2",new BigDecimal("130.00"));
        Product product5 = new Product(5L,"product2",new BigDecimal("70.00"));


        List<Product> productList = Arrays.asList(product1,product2,product3,product4,product5);

        List<Product> filtered = productList
                .stream()
                .filter(p -> p.getPrice().compareTo(new BigDecimal("100.00")) >= 0)
                .toList();

        System.out.println(filtered);

    }
}

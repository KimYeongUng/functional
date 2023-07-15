package functional;

import java.math.BigDecimal;

@FunctionalInterface
public interface BigDecimalToCurrency {
    String toCurrency(BigDecimal value);
}

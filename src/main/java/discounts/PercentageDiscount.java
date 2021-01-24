package discounts;

import shop.Product;
import java.math.BigDecimal;
import java.util.ArrayList;

public class PercentageDiscount implements BaseDiscount {

    @Override
    public BigDecimal discount(ArrayList<Product> products) {
        BigDecimal sum = BigDecimal.ZERO;
        for (var product : products) {
            sum = product.price().multiply(BigDecimal.valueOf(product.quantity())).add(sum);
        }
        if (sum.intValue() > 500) sum = sum.multiply(BigDecimal.valueOf(0.9));
        return sum;
    }
}

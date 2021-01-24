package discounts;

import shop.Product;
import java.math.BigDecimal;
import java.util.ArrayList;

public class QuantityDiscount implements BaseDiscount {

    @Override
    public BigDecimal discount(ArrayList<Product> products) {
        int discountLimit = 2;
        int quantity = 0;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal cheapest = products.get(0).price();

        for (var product: products) {
            for (int i = 0; i < product.quantity(); i++) {
                quantity++;
                sum = product.price().add(sum);
                if (product.price().intValue() < cheapest.intValue()) cheapest = product.price();
            }
        }
        if (quantity > discountLimit) sum.subtract(cheapest);
        return sum;
    }
}

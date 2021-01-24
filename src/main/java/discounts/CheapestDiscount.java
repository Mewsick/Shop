package discounts;

import shop.Product;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CheapestDiscount implements BaseDiscount {

    @Override
    public BigDecimal discount(ArrayList<Product> products) {
        return calculateCheapest(products);
    }

    public  BigDecimal calculateCheapest (ArrayList<Product> products) {
        var cheapest = products.get(0).price();
        int quantity = 0;
        BigDecimal sum = BigDecimal.ZERO;

        for (int i = 0; i < products.size(); i++) {
            var product = products.get(i);
            for (int j = 0; j < product.quantity(); j++) {
                quantity++;
                sum = product.price().add(sum);
                if (product.price().intValue() < cheapest.intValue()) cheapest = product.price();
            }
            if (quantity > 5) sum.subtract(cheapest);
        }
        return sum;
    }
}



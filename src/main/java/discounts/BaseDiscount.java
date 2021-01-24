package discounts;

import shop.Product;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface BaseDiscount {
    BigDecimal discount(ArrayList<Product> products);
}

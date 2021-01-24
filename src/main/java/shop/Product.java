package shop;

import command.CommandManager;

import java.math.BigDecimal;

public class Product {
    private CommandManager commandManager;
    private final BigDecimal price;
    private final ProductProps productProps;
    private int quantity;

    public Product(ProductProps productProps, double price, int quantity, CommandManager commandManager) {
        this.commandManager = commandManager;
        this.price = BigDecimal.valueOf(price);
        this.productProps = productProps;
        this.quantity = quantity;
    }

    public int quantity(){
        return quantity;
    }

    public ProductProps product() {
        return productProps;
    }

    public BigDecimal price() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product lineItem = (Product) o;

        if (quantity != lineItem.quantity) return false;
        if (!price.equals(lineItem.price)) return false;
        return productProps.equals(lineItem.productProps);
    }

    @Override
    public int hashCode() {
        int result = price.hashCode();
        result = 31 * result + productProps.hashCode();
        result = 31 * result + quantity;
        return result;
    }
}

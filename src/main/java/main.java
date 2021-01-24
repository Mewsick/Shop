import command.CommandManager;
import shop.ProductProps;
import shop.Cart;
import shop.Product;
import discounts.*;

public class main {

    public static void main(String[] args) {
        CommandManager manager = new CommandManager();
        Cart cart = new Cart(manager);

        Product product1 = new Product(new ProductProps("Gloves"), 30, 3, manager);
        Product product2 = new Product(new ProductProps("Boots"), 700, 1, manager);
        Product product3 = new Product(new ProductProps("Scarf"), 90, 2, manager);

        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);

        cart.addDiscount(new CheapestDiscount());
        cart.addDiscount(new PercentageDiscount());
        cart.addDiscount(new QuantityDiscount());

        System.out.println("\n\nBase receipt");
        System.out.println(cart.receipt());

        cart.undo();
        System.out.println("\n\n\n\n\n\nReceipt after undo (takes away last added product)");
        System.out.println(cart.receipt());

        cart.redo();
        System.out.println("\n\n\n\n\n\nReceipt after redo");
        System.out.println(cart.receipt());

        cart.removeProduct(product2);
        System.out.println("\n\n\n\n\n\nReceipt with product removed");
        System.out.println(cart.receipt());

    }
}

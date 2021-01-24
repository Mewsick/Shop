package shop;

import command.Command;
import command.CommandManager;
import discounts.BaseDiscount;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Cart {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<BaseDiscount> discounts = new ArrayList<>();
    private CommandManager commandManager;
    BigDecimal bestDiscount = BigDecimal.ZERO;
    BigDecimal sum = BigDecimal.ZERO;

    public Cart(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void addProduct(Product product) {
        products.add(product);

        Command undoCommand = new Command() {
            @Override
            public void execute() {
                products.remove(product);
            }

            @Override
            public void redo() {
                products.add(product);
            }
        };
        commandManager.addToUndo(undoCommand);
    }

    public void addDiscount(BaseDiscount discount) {
        discounts.add(discount);
    }

    public BigDecimal calculatePrice() {
        sum = BigDecimal.ZERO;
        for (var product : products) {
            sum = product.price().multiply(BigDecimal.valueOf(product.quantity())).add(sum);
        }
        for (var discount : discounts) {
            BigDecimal totalDiscount = discount.discount(products);
            if (sum.subtract(totalDiscount).intValue() > bestDiscount.intValue()) {
                bestDiscount = sum.subtract(totalDiscount);
            }
        }
        return sum.subtract(bestDiscount);
    }

    public String receipt() {
        calculatePrice();
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------\n");
        var list = products.stream()
                .sorted(Comparator.comparing(item -> item.product().name()))
                .collect(Collectors.toList());
        for (var each : list) {
            sb.append(String.format("%-24s % 7.2f \nquantity: %d%n\n", each.product().name(), each.price(), each.quantity()));
        }
        sb.append("--------------------------------\n");
        sb.append(String.format("%24s% 8.2f", "Total before discount:", sum));
        sb.append(String.format("\n%24s% 8.2f", "Discount:", bestDiscount.multiply(BigDecimal.valueOf(-1))));
        sb.append(String.format("\n%24s% 8.2f", "Total:", calculatePrice()));
        return sb.toString();
    }

    public void undo() {
        int index = commandManager.getUndoList().size() - 1;
        commandManager.getUndoList().get(index).execute();
        commandManager.getRedoList().push(commandManager.getUndoList().peek());
        commandManager.getUndoList().pop();
    }

    public void redo() {
        if (commandManager.getRedoList().size() > 0) commandManager.getRedoList().get(0).redo();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        Command addCommand = new Command() {
            @Override
            public void execute() {
                products.add(product);
            }

            @Override
            public void redo() {
                products.remove(product);
            }
        };
        commandManager.addToUndo(addCommand);
    }
}

package shop;

import java.util.UUID;

public class ProductProps {
    private final UUID id;
    private final String name;

    public ProductProps(String name) {
        this.name = name;
        id = UUID.randomUUID();
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductProps productProps = (ProductProps) o;

        if (!id.equals(productProps.id)) return false;
        return name.equals(productProps.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}

package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private static int nextId = 0;
    private final String number;

    private final Collection<Product> products = new ArrayList<>();

    public Invoice() {
        this.number = "FV/" + (nextId++);
    }

    public String getNumber() {
        return number;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException();
        }
        products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0 || product == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < quantity; i++) {
            addProduct(product);
        }
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Product product : products) {
            subtotal = subtotal.add(product.getPrice());
        }
        return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal totalTax = BigDecimal.ZERO;
        for (Product product : products) {
            BigDecimal taxValue = product.getPriceWithTax().subtract(product.getPrice());
            totalTax = totalTax.add(taxValue);
        }
        return totalTax;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : products) {
            total = total.add(product.getPriceWithTax());
        }
        return total;
    }

    public String printProducts() {
        Map<Product, Integer> quantities = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        sb.append(number).append("\n");

        for (Product product : products) {

            if (quantities.containsKey(product)) {
                Integer quantity = quantities.get(product);
                quantities.put(product, quantity + 1);
            } else {
                quantities.put(product, 1);
            }
        }

        for (Product product : quantities.keySet()) {

            sb.append(product.getName())
                    .append(", ")
                    .append(quantities.get(product))
                    .append(", ")
                    .append(product.getPrice())
                    .append("\n");
        }

        sb.append("Liczba pozycji: ")
                .append(products.size())
                .append("\n");

        return sb.toString();

    }
}

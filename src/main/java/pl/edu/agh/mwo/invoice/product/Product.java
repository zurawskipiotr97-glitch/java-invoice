package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    private final BigDecimal exciseTax;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null || price == null || tax == null || name.isEmpty()
                || price.compareTo(BigDecimal.ZERO) < 0 || tax.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.price = price;
        this.taxPercent = tax;
        this.exciseTax = BigDecimal.ZERO;
    }

    protected Product(String name, BigDecimal price, BigDecimal tax, BigDecimal exciseTax) {
        if (name == null || price == null || tax == null || exciseTax == null || name.isEmpty()
                || price.compareTo(BigDecimal.ZERO) < 0
                || tax.compareTo(BigDecimal.ZERO) < 0
                || exciseTax.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.price = price;
        this.taxPercent = tax;
        this.exciseTax = exciseTax;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTaxPercent() {
        return taxPercent;
    }

    public BigDecimal getPriceWithTax() {
        return price.add(price.multiply(taxPercent)).add(exciseTax);
    }

    public BigDecimal getExciseTax() {
        return exciseTax;
    }

    @Override
    public boolean equals(Object object) {
        Product product = (Product) object;

        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

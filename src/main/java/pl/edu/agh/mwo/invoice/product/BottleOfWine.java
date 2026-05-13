package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleOfWine extends Product {

    private static final BigDecimal TAX = new BigDecimal("0.23");
    private static final BigDecimal EXCISE_TAX = new BigDecimal("5.56");

    public BottleOfWine(String name, BigDecimal price) {
        super(name, price, TAX, EXCISE_TAX);
    }
}
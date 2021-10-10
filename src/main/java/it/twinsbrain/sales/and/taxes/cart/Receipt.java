package it.twinsbrain.sales.and.taxes.cart;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public class Receipt {

    private final List<CartItem> itemsWithTaxes;

    public Receipt(final List<CartItem> itemsWithTaxes) {

        this.itemsWithTaxes = itemsWithTaxes;
    }

    public String print() {
        return listItems()
                + "Sales Taxes: " + taxesSum() + "\n"
                + "Total: " + totalPrice();
    }

    private BigDecimal totalPrice() {
        return itemsWithTaxes.stream()
                .map(i -> i.priceWithTaxes).reduce(ZERO, BigDecimal::add);
    }

    private BigDecimal taxesSum() {
        return itemsWithTaxes.stream()
                .map(i -> i.taxes).reduce(ZERO, BigDecimal::add);
    }

    private String listItems() {
        return itemsWithTaxes.stream()
                .map(CartItem::toString).reduce("", (acc, curr) -> acc + curr + "\n");
    }
}

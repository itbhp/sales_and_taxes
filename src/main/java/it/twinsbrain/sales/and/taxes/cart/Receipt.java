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
        String receipt = itemsWithTaxes.stream()
                .map(CartItem::toString).reduce("", (acc, curr) -> acc + curr + "\n");
        final BigDecimal taxesSum = itemsWithTaxes.stream()
                .map(i -> i.taxes).reduce(ZERO, BigDecimal::add);
        final BigDecimal totalPrice = itemsWithTaxes.stream()
                .map(i -> i.priceWithTaxes).reduce(ZERO, BigDecimal::add);

        receipt += "Sales Taxes: " + taxesSum + "\n";
        receipt += "Total: " + totalPrice;
        return receipt;
    }
}

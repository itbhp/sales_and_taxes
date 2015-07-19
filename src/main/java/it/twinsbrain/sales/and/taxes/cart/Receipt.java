package it.twinsbrain.sales.and.taxes.cart;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    private final List<CartItem> itemsWithTaxes;

    public Receipt(final List<CartItem> itemsWithTaxes) {

        this.itemsWithTaxes = itemsWithTaxes;
    }

    public String print(){
        String receipt = itemsWithTaxes.stream().map(CartItem::toString).reduce("", (acc, curr) -> acc + curr + "\n");
        final BigDecimal taxesSum = itemsWithTaxes.stream().map(i -> i.taxes).reduce(BigDecimal.ZERO, BigDecimal::add);
        final BigDecimal totalPrice = itemsWithTaxes.stream().map(i -> i.priceWithTaxes).reduce(BigDecimal.ZERO, BigDecimal::add);

        receipt += "Sales Taxes: "+taxesSum.setScale(2,BigDecimal.ROUND_HALF_UP)+"\n";
        receipt += "Total: "+totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        return receipt;
    }
}

package it.twinsbrain.sales.and.taxes.cart;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author paolo
 */
public class Receipt {

    private List<CartItem> itemsWithTaxes;

    public Receipt(List<CartItem> itemsWithTaxes) {

        this.itemsWithTaxes = itemsWithTaxes;
    }

    public String print(){
        String receipt = itemsWithTaxes.stream().map(CartItem::toString).reduce("", (a, b) -> a + b + "\n");
        BigDecimal taxesSum = itemsWithTaxes.stream().map(c -> c.taxes).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPrice = itemsWithTaxes.stream().map(c -> c.priceWithTaxes).reduce(BigDecimal.ZERO, BigDecimal::add);
        receipt += "Sales Taxes: "+taxesSum.setScale(2,BigDecimal.ROUND_HALF_UP)+"\n";
        receipt += "Total: "+totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        return receipt;
    }
}

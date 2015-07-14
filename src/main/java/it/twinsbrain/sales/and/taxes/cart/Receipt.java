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

        String receipt = "";
        BigDecimal taxesSum = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(CartItem itemWithTax: itemsWithTaxes){

            receipt += createReceiptForItem(itemWithTax);
            taxesSum = taxesSum.add(itemWithTax.taxes);
            totalPrice = totalPrice.add(itemWithTax.priceWithTaxes);
        }

        receipt += "Sales Taxes: "+taxesSum.setScale(2,BigDecimal.ROUND_HALF_UP)+"\n";
        receipt += "Total: "+totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        return receipt;
    }

    private String createReceiptForItem(CartItem item) {
        return item.quantity + " " + item.description+ ": "+item.priceWithTaxes+ "\n";
    }

}

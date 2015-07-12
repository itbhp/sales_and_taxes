package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.parser.CartItemParser;
import it.twinsbrain.sales.and.taxes.strategies.TaxStrategyFactory;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author paolo
 */
public class ShoppingCart {

    private CartItemParser parser;
    private TaxStrategyFactory taxStrategyFactory;

    private List<CartItem> items;

    public ShoppingCart(CartItemParser parser, TaxStrategyFactory taxStrategyFactory) {
        this.parser = parser;
        this.taxStrategyFactory = taxStrategyFactory;
        this.items = new LinkedList<>();
    }

    protected void createCartItemsFrom(String input) {
        String[] lines = input.split("\n");
        for (String line : lines) {
            items.add(createCartItemFrom(line));
        }
    }

    protected List<CartItem> listInputItems() {
        return Collections.unmodifiableList(items);
    }

    private CartItem createCartItemFrom(String input) {
        String description = parser.readDescription(input);
        int quantity = parser.readQuantity(input);
        ProductType type = parser.readProductType(input);
        BigDecimal price = parser.readPrice(input);
        return new CartItem.Builder()
                .withType(type)
                .withQuantity(quantity)
                .withPrice(price)
                .withDescription(description)
                .build();
    }

    public String receipt(String order){
        createCartItemsFrom(order);
        String receipt = "";
        BigDecimal taxesSum = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;

        for(CartItem item: items){
            CartItem itemWithTax = item.accept(taxStrategyFactory.createFrom(item));
            receipt += createReceiptForItem(itemWithTax);
            receipt += "\n";
            taxesSum = taxesSum.add(itemWithTax.taxes);
            totalPrice = totalPrice.add(itemWithTax.priceWithTaxes);
        }
        receipt += "Sales Taxes: "+taxesSum.setScale(2,BigDecimal.ROUND_HALF_UP)+"\n";
        receipt += "Total: "+totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        return receipt;
    }

    private String createReceiptForItem(CartItem item) {
        return item.quantity + " " + item.description+ ": "+item.priceWithTaxes;
    }


}

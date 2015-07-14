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
    public ShoppingCart createFrom(String order) {
        String[] orderItems = order.split("\n");
        for (String orderItem : orderItems) {
            items.add(createCartItemFrom(orderItem));
        }
        return this;
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

    public Receipt toReceipt(){
        List<CartItem> itemsWithTaxes = new LinkedList<>();

        for(CartItem item: items){
            CartItem itemWithTax = item.accept(taxStrategyFactory.createFrom(item));
            itemsWithTaxes.add(itemWithTax);
        }

        return new Receipt(itemsWithTaxes);
    }
}

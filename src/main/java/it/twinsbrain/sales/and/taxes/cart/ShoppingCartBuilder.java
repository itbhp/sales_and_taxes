package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.parser.CartItemParser;
import it.twinsbrain.sales.and.taxes.strategies.TaxStrategyFactory;

import java.math.BigDecimal;

/**
 * @author paolo
 */
public class ShoppingCartBuilder {

    private final CartItemParser parser;
    private final TaxStrategyFactory taxStrategyFactory;

    public ShoppingCartBuilder(CartItemParser parser, TaxStrategyFactory taxStrategyFactory) {
        this.parser = parser;
        this.taxStrategyFactory = taxStrategyFactory;
    }

    public ShoppingCart createShoppingCartFrom(String order) {
        ShoppingCart cart = new ShoppingCart(taxStrategyFactory);
        String[] orderItems = order.split("\n");
        for (String orderItem : orderItems) {
            cart.add(createCartItemFrom(orderItem));
        }
        return cart;
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
}

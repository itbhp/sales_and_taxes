package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.parser.CartItemParser;
import it.twinsbrain.sales.and.taxes.strategies.TaxStrategyFactory;

import java.math.BigDecimal;

public class ShoppingCartBuilder {

    private final CartItemParser parser;
    private final TaxStrategyFactory taxStrategyFactory;

    public ShoppingCartBuilder(final CartItemParser parser, final TaxStrategyFactory taxStrategyFactory) {
        this.parser = parser;
        this.taxStrategyFactory = taxStrategyFactory;
    }

    public ShoppingCart createShoppingCartFrom(final String order) {
        final ShoppingCart cart = new ShoppingCart(taxStrategyFactory);
        final String[] orderItems = order.split("\n");
        for (String orderItem : orderItems) {
            cart.add(createCartItemFrom(orderItem));
        }
        return cart;
    }

    private CartItem createCartItemFrom(String input) {
        return new CartItem.Builder()
                .withType(parser.readProductType(input))
                .withQuantity(parser.readQuantity(input))
                .withPrice(parser.readPrice(input))
                .withDescription(parser.readDescription(input))
                .build();
    }
}

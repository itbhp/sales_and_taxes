package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.parser.CartItemParser;
import it.twinsbrain.sales.and.taxes.strategies.TaxStrategyFactory;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class ShoppingCartBuilder {

    private final CartItemParser parser;
    private final TaxStrategyFactory taxStrategyFactory;

    public ShoppingCartBuilder(
            final CartItemParser parser,
            final TaxStrategyFactory taxStrategyFactory
    ) {
        this.parser = parser;
        this.taxStrategyFactory = taxStrategyFactory;
    }

    public ShoppingCart createShoppingCartFrom(final String order) {
        return new ShoppingCart(
                taxStrategyFactory,
                stream(order.split("\n"))
                        .map(this::createCartItemFrom)
                        .collect(toList())
        );
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

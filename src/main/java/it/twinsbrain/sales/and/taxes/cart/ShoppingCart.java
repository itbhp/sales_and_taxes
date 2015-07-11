package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.parser.ProductParser;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author paolo
 */
public class ShoppingCart {

    private ProductParser parser;
    private List<CartItem> items;

    public ShoppingCart(ProductParser parser) {
        this.parser = parser;
        this.items = new LinkedList<>();
    }

    public void read(String input) {
        String description = parser.readDescription(input);
        int quantity = parser.readQuantity(input);
        ProductType type = parser.readProductType(input);
        BigDecimal price = parser.readPrice(input);
        items.add(new CartItem(type, quantity, price, description));
    }

    public List<CartItem> list() {
        return Collections.unmodifiableList(items);
    }
}

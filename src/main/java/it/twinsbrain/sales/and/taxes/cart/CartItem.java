package it.twinsbrain.sales.and.taxes.cart;

import java.math.BigDecimal;

/**
 * @author angelosciarra
 */
public class CartItem {

    public final ProductType type;
    public final int quantity;
    public final BigDecimal price;
    public final String description;

    public CartItem(ProductType type, int quantity, BigDecimal price, String description) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

}

package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.parser.CartItemParser;
import it.twinsbrain.sales.and.taxes.strategies.TaxStrategyFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.BOOK;
import static it.twinsbrain.sales.and.taxes.cart.ProductType.MUSIC;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ShoppingCartBuilderTest {

    private ShoppingCartBuilder underTest;
    private String input;
    private ShoppingCart shoppingCart;

    @Before
    public void setup() {
        underTest = new ShoppingCartBuilder(new CartItemParser(), new TaxStrategyFactory());
    }

    @Test
    public void one_product_one_cart_item() {
        input = "1 music cd at 12.99";

        shoppingCart = underTest.createShoppingCartFrom(input);

        List<CartItem> cartItems = shoppingCart.items();
        assertThat(cartItems.size(), is(1));

        CartItem cartItem = cartItems.get(0);
        assertThat(cartItem.quantity, is(1));
        assertThat(cartItem.description, is(equalTo("music cd")));
        assertThat(cartItem.price, comparesEqualTo(new BigDecimal("12.99")));
        assertThat(cartItem.type, is(MUSIC));
    }

    @Test
    public void two_products_two_cart_items() {
        input = "1 music cd at 12.99\n1 Harry Potter book at 25.99";

        shoppingCart = underTest.createShoppingCartFrom(input);

        List<CartItem> cartItems = shoppingCart.items();
        assertThat(cartItems.size(), is(2));

        CartItem first = cartItems.get(0);
        assertThat(first.quantity, is(1));
        assertThat(first.description, is(equalTo("music cd")));
        assertThat(first.price, comparesEqualTo(new BigDecimal("12.99")));
        assertThat(first.type, is(MUSIC));

        CartItem second = cartItems.get(1);
        assertThat(second.quantity, is(1));
        assertThat(second.description, is(equalTo("Harry Potter book")));
        assertThat(second.price, comparesEqualTo(new BigDecimal("25.99")));
        assertThat(second.type, is(BOOK));
    }
}

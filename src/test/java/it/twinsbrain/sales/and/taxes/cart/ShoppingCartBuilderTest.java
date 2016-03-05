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

        List<CartItem> cartItems = shoppingCart.cartItems();
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

        List<CartItem> cartItems = shoppingCart.cartItems();
        assertThat(cartItems.size(), is(2));

        CartItem cartItem1 = cartItems.get(0);
        assertThat(cartItem1.quantity, is(1));
        assertThat(cartItem1.description, is(equalTo("music cd")));
        assertThat(cartItem1.price, comparesEqualTo(new BigDecimal("12.99")));
        assertThat(cartItem1.type, is(MUSIC));

        CartItem cartItem2 = cartItems.get(1);
        assertThat(cartItem2.quantity, is(1));
        assertThat(cartItem2.description, is(equalTo("Harry Potter book")));
        assertThat(cartItem2.price, comparesEqualTo(new BigDecimal("25.99")));
        assertThat(cartItem2.type, is(BOOK));
    }
}

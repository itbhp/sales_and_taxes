package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.parser.CartItemParser;
import it.twinsbrain.sales.and.taxes.strategies.TaxStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.BOOK;
import static it.twinsbrain.sales.and.taxes.cart.ProductType.MUSIC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ShoppingCartBuilderTest {

    private ShoppingCartBuilder underTest;
    private String input;
    private ShoppingCart shoppingCart;

    @BeforeEach
    public void setUp() {
        underTest = new ShoppingCartBuilder(new CartItemParser(), new TaxStrategyFactory());
    }

    @Test
    public void oneProductOneCartItem() {
        input = "1 music cd at 12.99";

        shoppingCart = underTest.createShoppingCartFrom(input);

        var cartItems = shoppingCart.items();
        assertThat(cartItems.size(), is(1));

        var cartItem = cartItems.get(0);
        assertThat(cartItem.quantity, is(1));
        assertThat(cartItem.description, is(equalTo("music cd")));
        assertThat(cartItem.price, comparesEqualTo(new BigDecimal("12.99")));
        assertThat(cartItem.type, is(MUSIC));
    }

    @Test
    public void twoProductsTwoCartItems() {
        input = "1 music cd at 12.99\n1 Harry Potter book at 25.99";

        shoppingCart = underTest.createShoppingCartFrom(input);

        var cartItems = shoppingCart.items();
        assertThat(cartItems.size(), is(2));

        var first = cartItems.get(0);
        assertThat(first.quantity, is(1));
        assertThat(first.description, is(equalTo("music cd")));
        assertThat(first.price, comparesEqualTo(new BigDecimal("12.99")));
        assertThat(first.type, is(MUSIC));

        var second = cartItems.get(1);
        assertThat(second.quantity, is(1));
        assertThat(second.description, is(equalTo("Harry Potter book")));
        assertThat(second.price, comparesEqualTo(new BigDecimal("25.99")));
        assertThat(second.type, is(BOOK));
    }
}

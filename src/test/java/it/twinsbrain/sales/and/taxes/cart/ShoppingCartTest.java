package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.parser.CartItemParser;
import it.twinsbrain.sales.and.taxes.strategies.TaxStrategyFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.BOOK;
import static it.twinsbrain.sales.and.taxes.cart.ProductType.MUSIC;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author paolo
 */
public class ShoppingCartTest {

    private ShoppingCart underTest;
    private String input;

    @Before
    public void setup(){
        underTest = new ShoppingCart(new CartItemParser(), new TaxStrategyFactory());
    }

    @Test
    public void one_product_one_cart_item(){
        given:{
            input = "1 music cd at 12.99";
        }
        when:{
            underTest.createCartItemsFrom(input);
        }
        then:{
            List<CartItem> cartItems = underTest.listInputItems();
            assertThat(cartItems.size(), is(1));

            CartItem cartItem = cartItems.get(0);
            assertThat(cartItem.quantity, is(1));
            assertThat(cartItem.description, is(equalTo("music cd")));
            assertThat(cartItem.price, is(equalTo(new BigDecimal("12.99"))));
            assertThat(cartItem.type, is(MUSIC));
        }
    }

    @Test
    public void two_products_two_cart_items(){
        given:{
            input = "1 music cd at 12.99\n1 Harry Potter book at 25.99";
        }
        when:{
            underTest.createCartItemsFrom(input);
        }
        then:{
            List<CartItem> cartItems = underTest.listInputItems();
            assertThat(cartItems.size(), is(2));

            CartItem cartItem1 = cartItems.get(0);
            assertThat(cartItem1.quantity, is(1));
            assertThat(cartItem1.description, is(equalTo("music cd")));
            assertThat(cartItem1.price, is(equalTo(new BigDecimal("12.99"))));
            assertThat(cartItem1.type, is(MUSIC));

            CartItem cartitem2 = cartItems.get(1);
            assertThat(cartitem2.quantity, is(1));
            assertThat(cartitem2.description, is(equalTo("Harry Potter book")));
            assertThat(cartitem2.price, is(equalTo(new BigDecimal("25.99"))));
            assertThat(cartitem2.type, is(BOOK));
        }
    }
}

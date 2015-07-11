package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.parser.CartItemParser;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

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
        underTest = new ShoppingCart(new CartItemParser());
    }

    @Test
    public void one_product_one_cart_item(){
        given:{
            input = "1 music cd at 12.99";
        }
        when:{
            underTest.read(input);
        }
        then:{
            List<CartItem> cartItems = underTest.list();
            assertThat(cartItems.size(), is(1));

            CartItem cartItem = cartItems.get(0);
            assertThat(cartItem.quantity, is(1));
            assertThat(cartItem.description, is(equalTo("music cd")));
            assertThat(cartItem.price, is(equalTo(new BigDecimal("12.99"))));
            assertThat(cartItem.type, is(MUSIC));
        }
    }
}

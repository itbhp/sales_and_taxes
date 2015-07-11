package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.parser.CartItemParser;
import org.junit.Before;
import org.junit.Test;

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
            assertThat(underTest.list().size(), is(1));
        }
    }
}

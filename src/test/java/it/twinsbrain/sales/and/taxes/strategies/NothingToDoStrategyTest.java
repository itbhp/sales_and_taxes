package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NothingToDoStrategyTest {

    private TaxStrategy underTest;

    @Before
    public void setup() {
        underTest = new NothingToDoStrategy();
    }


    @Test
    public void itShouldDoNothing() {
        CartItem item = new CartItem.Builder().withPrice(new BigDecimal("1.99")).build();

        item = underTest.updateTaxesOn(item);

        assertThat(item.priceWithTaxes, is(equalTo(item.price)));
    }
}

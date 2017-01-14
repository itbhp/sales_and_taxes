package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.assertThat;

public class NothingToDoStrategyTest {

    private TaxStrategy underTest;

    @Before
    public void setUp() {
        underTest = new NothingToDoStrategy();
    }


    @Test
    public void itShouldDoNothing() {
        CartItem item = new CartItem.Builder().withPrice(new BigDecimal("1.99")).build();

        item = underTest.updateTaxesOn(item);

        assertThat(item.priceWithTaxes, comparesEqualTo(item.price));
    }
}

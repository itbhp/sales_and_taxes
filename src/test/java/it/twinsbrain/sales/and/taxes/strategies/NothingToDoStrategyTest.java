package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

public class NothingToDoStrategyTest {

    private TaxStrategy underTest;

    @BeforeEach
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

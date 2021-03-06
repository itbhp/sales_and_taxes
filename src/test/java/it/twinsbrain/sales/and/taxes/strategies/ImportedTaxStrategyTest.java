package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.assertThat;

public class ImportedTaxStrategyTest {

    private TaxStrategy underTest;
    private CartItem item;

    @Before
    public void setUp() {
        underTest = new ImportedTaxStrategy();
    }

    @Test
    public void itShouldApplyFivePercentTax() {
        item = new CartItem.Builder().withPrice(new BigDecimal("27.99")).build();

        item = underTest.updateTaxesOn(item);

        assertThat("price should be five percent more", item.priceWithTaxes,
                comparesEqualTo(new BigDecimal("29.39")));
    }

    @Test
    public void importedChocolate() {
        item = new CartItem.Builder().withPrice(new BigDecimal("11.25")).build();

        item = underTest.updateTaxesOn(item);

        assertThat("price should be five percent more",
                item.priceWithTaxes, comparesEqualTo(new BigDecimal("11.85")));
    }
}

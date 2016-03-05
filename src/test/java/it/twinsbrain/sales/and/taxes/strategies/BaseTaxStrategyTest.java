package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.assertThat;

public class BaseTaxStrategyTest {

    private TaxStrategy underTest;

    @Before
    public void setUp() {
        underTest = new BaseTaxStrategy();
    }

    @Test
    public void itShouldApplyTenPercentTax() {

        CartItem item = new CartItem.Builder().withPrice(new BigDecimal("1.00")).build();

        CartItem updatedItem = underTest.updateTaxesOn(item);

        assertThat("item visited should not change",
                item.priceWithTaxes, comparesEqualTo(new BigDecimal("1.00")));
        assertThat("price should be 10 percent more",
                updatedItem.priceWithTaxes, comparesEqualTo(new BigDecimal("1.10")));
    }
}

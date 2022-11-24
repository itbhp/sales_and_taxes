package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

public class BaseTaxStrategyTest {

    private TaxStrategy underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BaseTaxStrategy();
    }

    @Test
    public void itShouldApplyTenPercentTax() {

        var item = new CartItem.Builder().withPrice(new BigDecimal("1.00")).build();

        var updatedItem = underTest.updateTaxesOn(item);

        assertThat("item visited should not change",
                item.priceWithTaxes, comparesEqualTo(new BigDecimal("1.00")));
        assertThat("price should be 10 percent more",
                updatedItem.priceWithTaxes, comparesEqualTo(new BigDecimal("1.10")));
    }
}

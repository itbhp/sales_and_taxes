package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.assertThat;

public class ComposedStrategyTest {

    private TaxStrategy underTest;
    private CartItem item;

    @Test
    public void itShouldApplyBothStrategy() {
        underTest = new ComposedStrategy(new ImportedTaxStrategy(), new NothingToDoStrategy());
        item = new CartItem.Builder().withPrice(new BigDecimal("1.00")).build();

        item = underTest.updateTaxesOn(item);

        assertThat("price should be five percent more",
                item.priceWithTaxes, comparesEqualTo(new BigDecimal("1.05")));
    }


    @Test
    public void importedBottleOfPerfume() {
        underTest = new ComposedStrategy(new ImportedTaxStrategy(), new BaseTaxStrategy());

        item = new CartItem.Builder().withPrice(new BigDecimal("27.99")).build();

        item = underTest.updateTaxesOn(item);

        assertThat(item.priceWithTaxes, comparesEqualTo(new BigDecimal("32.19")));
    }
}

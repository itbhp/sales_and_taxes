package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author paolo
 */
public class ComposedStrategyTest {

    private TaxStrategy underTest;
    private CartItem item;

    @Test
    public void itShouldApplyBothStrategy(){
        given:{
            underTest = new ComposedStrategy(new ImportedTaxStrategy(), new NothingToDoStrategy());
            item = new CartItem.Builder().withPrice(new BigDecimal("1.00")).build();
        }
        when:{
            item = underTest.updateItemWithTaxes(item);
        }
        then:{
            assertThat("price should be five percent more", item.priceWithTaxes, is(equalTo(new BigDecimal("1.05"))));
        }
    }

    @Test
    public void itShouldApplyBothTaxes(){
        given: {
            underTest = new ComposedStrategy(new ImportedTaxStrategy(), new BaseTaxStrategy());
            item = new CartItem.Builder().withPrice(new BigDecimal("1.00")).build();
        }
        when:{
            item = underTest.updateItemWithTaxes(item);
        }
        then:{
            assertThat(item.priceWithTaxes, is(equalTo(new BigDecimal("1.16"))));
        }
    }

    @Test
    public void importedBottleOfPerfume(){
        given: {
            underTest = new ComposedStrategy(new ImportedTaxStrategy(), new BaseTaxStrategy());
            item = new CartItem.Builder().withPrice(new BigDecimal("27.99")).build();
        }
        when:{
            item = underTest.updateItemWithTaxes(item);
        }
        then:{
            assertThat(item.priceWithTaxes, is(equalTo(new BigDecimal("32.33"))));
        }
    }
}

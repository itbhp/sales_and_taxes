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

    @Before
    public void setup(){
        underTest = new ComposedStrategy(new ImportedTaxStrategy(), new NothingToDoStrategy());
    }

    @Test
    public void itShouldApplyBothStrategy(){
        given:{
            item = new CartItem.Builder().withPrice(new BigDecimal("1.00")).build();
        }
        when:{
            item = underTest.updateItemWithTaxes(item);
        }
        then:{
            assertThat("price should be five percent more", item.priceWithTaxes, is(equalTo(new BigDecimal("1.05"))));
        }
    }
}

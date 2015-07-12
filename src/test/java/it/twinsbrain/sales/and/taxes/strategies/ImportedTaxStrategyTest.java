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
public class ImportedTaxStrategyTest {

    private TaxStrategy underTest;
    private CartItem item;

    @Before
    public void setup(){
        underTest = new ImportedTaxStrategy();
    }

    @Test
    public void itShouldApplyFivePercentTax(){
        given:{
            item = new CartItem.Builder().withPrice(new BigDecimal("27.99")).build();
        }
        when:{
            item = underTest.updateTaxesOn(item);
        }
        then:{
            assertThat("price should be five percent more", item.priceWithTaxes, is(equalTo(new BigDecimal("29.39"))));
        }
    }

    @Test
    public void imported_chocolate(){
        given:{
            item = new CartItem.Builder().withPrice(new BigDecimal("11.25")).build();
        }
        when:{
            item = underTest.updateTaxesOn(item);
        }
        then:{
            assertThat("price should be five percent more", item.priceWithTaxes, is(equalTo(new BigDecimal("11.85"))));
        }
    }
}

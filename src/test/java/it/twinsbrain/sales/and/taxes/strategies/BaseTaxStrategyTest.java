package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author paolo
 */
public class BaseTaxStrategyTest {

    private TaxStrategy underTest;
    private CartItem item;

    @Before
    public void setUp(){
        underTest = new BaseTaxStrategy();
    }

    @Test
    public void itShouldApplyTenPercentTax(){

        given:{
            item = new CartItem.Builder().withPrice(new BigDecimal("1.00")).build();
        }

        when:{
            item = underTest.updateTaxesOn(item);
        }

        then:{

            assertThat("price should be 10 percent more", item.priceWithTaxes, is(equalTo(new BigDecimal("1.10"))));
        }
    }
}

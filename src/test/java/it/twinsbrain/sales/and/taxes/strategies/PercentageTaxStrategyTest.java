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
public class PercentageTaxStrategyTest {

    PercentageTaxStrategy underTest;
    private CartItem item;

    @Before
    public void setup(){
        underTest = new PercentageTaxStrategy() {
            @Override
            public CartItem visit(CartItem visitee) {
                return null;
            }
        };
    }

    @Test
    public void round_value_under_threshold(){
        given:{
            item = new CartItem.Builder().withPrice(new BigDecimal("1.55")).build();
        }
        when:{
            item = underTest.applyPercentageTax(item, "0.1");
        }
        then:{
            assertThat(item.priceWithTaxes, is(equalTo(new BigDecimal("1.71")))); //1.705 -> ceil up
        }
    }

    @Test
    public void round_value_over_threshold(){
        given:{
            item = new CartItem.Builder().withPrice(new BigDecimal("1.54")).build();
        }
        when:{
            item = underTest.applyPercentageTax(item, "0.1");
        }
        then:{
            assertThat(item.priceWithTaxes, is(equalTo(new BigDecimal("1.70")))); //1.694 -> ceil up
        }
    }

}

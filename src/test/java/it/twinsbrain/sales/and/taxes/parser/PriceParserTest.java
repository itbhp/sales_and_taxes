package it.twinsbrain.sales.and.taxes.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class PriceParserTest {
    private CartItemParser underTest;

    private String input;
    private BigDecimal price;

    @Before
    public void setup(){
        underTest = new CartItemParser();
    }

    @Test
    public void itCanReadPrice(){
        given:{
            input = "1 chocolate bar at 0.85";
        }
        when:{
            price = underTest.readPrice(input);
        }
        then:{
            Assert.assertThat(price, is(equalTo(new BigDecimal("0.85"))));
        }
    }
}

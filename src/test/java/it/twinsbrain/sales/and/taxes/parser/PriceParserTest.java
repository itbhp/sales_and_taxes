package it.twinsbrain.sales.and.taxes.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PriceParserTest {
    private CartItemParser underTest;

    @Before
    public void setup() {
        underTest = new CartItemParser();
    }

    @Test
    public void itCanReadPrice() {
        String input = "1 chocolate bar at 0.85";

        BigDecimal price = underTest.readPrice(input);

        assertThat(price, is(equalTo(new BigDecimal("0.85"))));
    }
}

package it.twinsbrain.sales.and.taxes.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

public class PriceParserTest {
    private CartItemParser underTest;

    @BeforeEach
    public void setUp() {
        underTest = new CartItemParser();
    }

    @Test
    public void itCanReadPrice() {
        String input = "1 chocolate bar at 0.85";

        BigDecimal price = underTest.readPrice(input);

        assertThat(price, comparesEqualTo(new BigDecimal("0.85")));
    }
}

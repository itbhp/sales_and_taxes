package it.twinsbrain.sales.and.taxes.parser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class QuantityParserTest {

    private CartItemParser underTest;

    private String sampleInput;
    private int quantity;

    @Before
    public void setUp() {
        underTest = new CartItemParser();
    }

    @Test
    public void quantityReadOnWellFormedInput() {
        sampleInput = "1 bottle of perfume: 31";

        quantity = underTest.readQuantity(sampleInput);

        assertThat(quantity, is(1));
    }

    @Test(expected = NumberFormatException.class)
    public void itShouldRaiseExceptionOnNotWellFormedInput() {
        sampleInput = "One bottle of perfume: 31";

        quantity = underTest.readQuantity(sampleInput);
    }
}

package it.twinsbrain.sales.and.taxes.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class QuantityParserTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private CartItemParser underTest;

    private String sampleInput;
    private int quantity;

    @Before
    public void setUp() {
        underTest = new CartItemParser();
    }

    @Test
    public void quantity_read_well_formed_input() {
        sampleInput = "1 bootle of perfume: 31";

        quantity = underTest.readQuantity(sampleInput);

        assertThat(quantity, is(1));
    }

    @Test
    public void exception_no_well_formed_input() {
        sampleInput = "One bootle of perfume: 31";

        exception.expect(NumberFormatException.class);

        quantity = underTest.readQuantity(sampleInput);
    }
}

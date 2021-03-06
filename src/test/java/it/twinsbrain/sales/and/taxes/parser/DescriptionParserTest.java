package it.twinsbrain.sales.and.taxes.parser;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DescriptionParserTest {

    private CartItemParser underTest;

    @Before
    public void setUp() {
        underTest = new CartItemParser();
    }

    @Test
    public void itCanReadDescription() {
        String input = "1 chocolate bar at 0.85";

        String description = underTest.readDescription(input);

        assertThat(description, is(equalTo("chocolate bar")));
    }
}

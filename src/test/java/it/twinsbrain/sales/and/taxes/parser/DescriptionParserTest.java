package it.twinsbrain.sales.and.taxes.parser;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class DescriptionParserTest {

    private CartItemParser underTest;

    @BeforeEach
    public void setUp() {
        underTest = new CartItemParser();
    }

    @Test
    public void itCanReadDescription() {
        var input = "1 chocolate bar at 0.85";

        var description = underTest.readDescription(input);

        assertThat(description, is(equalTo("chocolate bar")));
    }
}

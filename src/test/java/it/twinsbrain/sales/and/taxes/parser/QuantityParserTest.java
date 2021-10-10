package it.twinsbrain.sales.and.taxes.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuantityParserTest {

    private CartItemParser underTest;

    private String sampleInput;

    @BeforeEach
    public void setUp() {
        underTest = new CartItemParser();
    }

    @Test
    public void quantityReadOnWellFormedInput() {
        sampleInput = "1 bottle of perfume: 31";

        int quantity = underTest.readQuantity(sampleInput);

        assertThat(quantity, is(1));
    }

    @Test
    public void itShouldRaiseExceptionOnNotWellFormedInput() {
        sampleInput = "One bottle of perfume: 31";

        assertThrows(NumberFormatException.class, () -> underTest.readQuantity(sampleInput));
    }
}
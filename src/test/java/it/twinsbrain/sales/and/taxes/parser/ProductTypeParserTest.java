package it.twinsbrain.sales.and.taxes.parser;

import it.twinsbrain.sales.and.taxes.cart.ProductType;
import org.junit.Before;
import org.junit.Test;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProductTypeParserTest {

    private CartItemParser underTest;

    private String input;
    private ProductType readProductType;

    @Before
    public void setUp() {
        underTest = new CartItemParser();
    }

    @Test
    public void chocolateIsFood() {
        input = "1 chocolate bar at 0.85";

        readProductType = underTest.readProductType(input);

        assertThat(readProductType, is(FOOD));
    }

    @Test
    public void breadIsFood() {
        input = "1 bread pack 20 slices at 2.85";

        readProductType = underTest.readProductType(input);

        assertThat(readProductType, is(FOOD));
    }

    @Test
    public void pillsAreMedicine() {
        input = "1 bottle of pills at 2.85";

        readProductType = underTest.readProductType(input);

        assertThat(readProductType, is(MEDICAL));
    }

    @Test
    public void anyBookIsABook() {
        input = "1 Moccia's book at 0.00";

        readProductType = underTest.readProductType(input);

        assertThat(readProductType, is(BOOK));
    }

    @Test
    public void aCdIsMusic() {
        input = "1 AC/DC the gold collection CD at 20.00";

        readProductType = underTest.readProductType(input);

        assertThat(readProductType, is(MUSIC));
    }

    @Test
    public void anMp3IsMusic() {
        input = "1 Dire Straits - Walk of Life mp3 at 0.99";

        readProductType = underTest.readProductType(input);

        assertThat(readProductType, is(MUSIC));
    }

    @Test
    public void anythingElseIsOthers() {
        input = "1 imported bottle of perfume at 27.99";

        readProductType = underTest.readProductType(input);

        assertThat(readProductType, is(OTHERS));
    }

}

package it.twinsbrain.sales.and.taxes.parser;

import it.twinsbrain.sales.and.taxes.cart.ProductType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author paolo
 */
public class DescriptionParserTest {

    private ProductParser underTest;

    private String input;
    private String description;

    @Before
    public void setup(){
        underTest = new ProductParser();
    }

    @Test
    public void itCanReadDescription(){
        given:{
            input = "1 chocolate bar at 0.85";
        }
        when:{
            description = underTest.readDescription(input);
        }
        then:{
            Assert.assertThat(description,is(equalTo("chocolate bar")));
        }
    }
}

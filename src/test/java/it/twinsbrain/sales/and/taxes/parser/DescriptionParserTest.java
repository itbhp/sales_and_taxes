package it.twinsbrain.sales.and.taxes.parser;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DescriptionParserTest {

    private CartItemParser underTest;

    private String input;
    private String description;

    @Before
    public void setup(){
        underTest = new CartItemParser();
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
            assertThat(description, is(equalTo("chocolate bar")));
        }
    }
}

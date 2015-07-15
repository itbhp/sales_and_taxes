package it.twinsbrain.sales.and.taxes.math;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RoundedDecimalTest {

    private RoundedDecimal underTest;
    private BigDecimal input;


    @Test
    public void it_should_round_to_the_nearest_two_cents() {
        given:
        {
            input = new BigDecimal("17.033");
        }
        when:
        {
            underTest = new RoundedDecimal(input, "2", "100");
        }
        then:
        {
            assertThat(underTest.getValue(), is(equalTo(new BigDecimal("17.04"))));
        }
    }

    @Test
    public void it_should_round_to_the_nearest_five_cents() {
        given:
        {
            input = new BigDecimal("17.033");
        }
        when:
        {
            underTest = new RoundedDecimal(input, "5", "100");
        }
        then:
        {
            assertThat(underTest.getValue(), is(equalTo(new BigDecimal("17.05"))));
        }
    }


}

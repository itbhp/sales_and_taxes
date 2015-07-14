package it.twinsbrain.sales.and.taxes.math;

import org.junit.Test;
import org.junit.runner.RunWith;
import net.avh4.test.junit.Nested;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author angelosciarra
 */
@RunWith(Nested.class)
public class RoundedTest {

    private static Rounded underTest;
    private static BigDecimal input;

    static class RoundUpToTwoCentsTest {

        @Test
        public void it_should_round_to_the_nearest_two_cents(){
            given:{
                input = new BigDecimal("17.003");
            }
            when:{
                underTest = new Rounded(input, "2", "100");
            }
            then: {
                assertThat(underTest.getValue(), is(equalTo(new BigDecimal("17.02"))));
            }
        }

    }

}

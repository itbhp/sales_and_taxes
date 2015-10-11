package it.twinsbrain.sales.and.taxes.math;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class RoundedDecimalTest {

    public static class TwoPercentTest{
        RoundedDecimal underTest;
        BigDecimal input;

        @Test
        public void belowTwoCentsDecimal() {
            given:
            {
                input = new BigDecimal("17.018");
            }
            when:
            {
                underTest = new RoundedDecimal(input, "2", "100");
            }
            then:
            {
                assertThat(underTest.getValue(), is(equalTo(new BigDecimal("17.02"))));
            }
        }

        @Test
        public void firstStepUponTwoCentsDecimal() {
            given:
            {
                input = new BigDecimal("17.023");
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
        public void secondStepUponTwoCentsDecimal() {
            given:
            {
                input = new BigDecimal("17.043");
            }
            when:
            {
                underTest = new RoundedDecimal(input, "2", "100");
            }
            then:
            {
                assertThat(underTest.getValue(), is(equalTo(new BigDecimal("17.06"))));
            }
        }
    }

    public static class FivePercentTest {
        RoundedDecimal underTest;
        BigDecimal input;

        @Test
        public void belowFiveCentsDecimal() {
            given:
            {
                input = new BigDecimal("17.018");
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

        @Test
        public void uponFiveCentsDecimal() {
            given:
            {
                input = new BigDecimal("17.053");
            }
            when:
            {
                underTest = new RoundedDecimal(input, "5", "100");
            }
            then:
            {
                assertThat(underTest.getValue().compareTo(new BigDecimal("17.10")),is(0));
            }
        }
    }


}

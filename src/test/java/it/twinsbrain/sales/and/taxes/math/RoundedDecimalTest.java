package it.twinsbrain.sales.and.taxes.math;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;

public class RoundedDecimalTest {

    @Nested
    public class TwoPercentTest {
        private RoundedDecimal underTest;
        private BigDecimal input;

        @Test
        public void belowTwoCentsDecimal() {
            input = new BigDecimal("17.018");

            underTest = new RoundedDecimal(input, "2", "100");

            assertThat(underTest.value(), is(comparesEqualTo(new BigDecimal("17.02"))));
        }

        @Test
        public void firstStepUponTwoCentsDecimal() {
            input = new BigDecimal("17.023");

            underTest = new RoundedDecimal(input, "2", "100");

            assertThat(underTest.value(), is(comparesEqualTo(new BigDecimal("17.04"))));
        }

        @Test
        public void secondStepUponTwoCentsDecimal() {
            input = new BigDecimal("17.043");

            underTest = new RoundedDecimal(input, "2", "100");

            assertThat(underTest.value(), is(comparesEqualTo(new BigDecimal("17.06"))));
        }
    }

    @Nested
    public class FivePercentTest {
        private RoundedDecimal underTest;
        private BigDecimal input;

        @Test
        public void belowFiveCentsDecimal() {
            input = new BigDecimal("17.018");

            underTest = new RoundedDecimal(input, "5", "100");

            assertThat(underTest.value(), comparesEqualTo(new BigDecimal("17.05")));
        }

        @Test
        public void uponFiveCentsDecimal() {
            input = new BigDecimal("17.053");

            underTest = new RoundedDecimal(input, "5", "100");

            assertThat(underTest.value(), is(comparesEqualTo(new BigDecimal("17.1"))));
        }
    }
}

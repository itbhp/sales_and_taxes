package it.twinsbrain.sales.and.taxes.math;

import java.math.BigDecimal;

public class RoundedDecimal {

    private final BigDecimal orig;
    private final BigDecimal roundFactor;

    public RoundedDecimal(final BigDecimal orig, final String nearestUnit, final String unitBase) {
        this.orig = orig;
        roundFactor = new BigDecimal(unitBase).divide(new BigDecimal(nearestUnit));
    }

    public BigDecimal getValue() {
         return ceil(orig.multiply(roundFactor)).divide(roundFactor);
    }

    private BigDecimal ceil(final BigDecimal value) {
        return new BigDecimal(Math.ceil(value.doubleValue()));
    }
}

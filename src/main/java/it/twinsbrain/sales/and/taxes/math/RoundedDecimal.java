package it.twinsbrain.sales.and.taxes.math;

import java.math.BigDecimal;

public class RoundedDecimal {

    private final BigDecimal value;

    public RoundedDecimal(final BigDecimal orig, final String nearestUnit, final String unitBase) {
        final BigDecimal roundFactor = new BigDecimal(unitBase).divide(new BigDecimal(nearestUnit));
        value = ceil(orig.multiply(roundFactor)).divide(roundFactor);
    }

    public BigDecimal value() {
        return value;
    }

    private BigDecimal ceil(final BigDecimal value) {
        return value.setScale(0, BigDecimal.ROUND_UP);
    }
}

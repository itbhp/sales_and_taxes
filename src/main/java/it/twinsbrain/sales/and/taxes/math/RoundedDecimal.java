package it.twinsbrain.sales.and.taxes.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundedDecimal {

    private final BigDecimal value;

    public RoundedDecimal(final BigDecimal orig, final String nearestUnit, final String unitBase) {
        var roundFactor = new BigDecimal(unitBase).divide(new BigDecimal(nearestUnit), 3, RoundingMode.HALF_UP);
        value = ceilUp(orig.multiply(roundFactor)).divide(roundFactor, 3, RoundingMode.HALF_UP);
    }

    public BigDecimal value() {
        return value;
    }

    private BigDecimal ceilUp(final BigDecimal value) {
        return value.setScale(0, RoundingMode.UP);
    }
}

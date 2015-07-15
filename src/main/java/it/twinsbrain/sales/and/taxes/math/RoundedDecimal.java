package it.twinsbrain.sales.and.taxes.math;

import java.math.BigDecimal;

public class RoundedDecimal {

    private final BigDecimal orig;
    private BigDecimal value;
    private BigDecimal roundFactor;

    public RoundedDecimal(BigDecimal orig, String upTo, String order) {
        this.orig = orig;
        roundFactor = new BigDecimal(order).divide(new BigDecimal(upTo));
    }

    public BigDecimal getValue() {
        if(value == null){
            value = ceil(orig.multiply(roundFactor)).divide(roundFactor);
        }
        return value;
    }

    private BigDecimal ceil(BigDecimal value) {
        return new BigDecimal(Math.ceil(value.doubleValue()));
    }
}

package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import it.twinsbrain.sales.and.taxes.math.RoundedDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class PercentageTaxStrategy implements TaxStrategy {
    private final RoundingMode roundingMode = RoundingMode.CEILING;

    protected CartItem applyPercentageTax(final CartItem visitee, final String taxPercentage) {
        final BigDecimal rate = new BigDecimal(taxPercentage);
        final BigDecimal taxes = new RoundedDecimal(visitee.price.multiply(rate), "5", "100").getValue();
        final BigDecimal priceWithTaxes = visitee.priceWithTaxes.add(taxes);

        int scale = 2;
        return new CartItem.Builder()
                .withType(visitee.type)
                .withQuantity(visitee.quantity)
                .withPrice(visitee.price)
                .withDescription(visitee.description)
                .withTaxes(taxes.add(visitee.taxes).setScale(scale, roundingMode))
                .withPriceWithTaxes(priceWithTaxes.setScale(scale, roundingMode))
                .withTaxPercentage(rate.add(visitee.taxPercentage).setScale(scale, roundingMode))
                .build();
    }
}

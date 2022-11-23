package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import it.twinsbrain.sales.and.taxes.cart.CartItem.Builder;
import it.twinsbrain.sales.and.taxes.math.RoundedDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static it.twinsbrain.sales.and.taxes.cart.CartItem.Change.change;

public abstract class PercentageTaxStrategy implements TaxStrategy {
    private final RoundingMode roundingMode = RoundingMode.CEILING;

    protected CartItem applyPercentageTax(final CartItem visitee, final String taxPercentage) {
        final BigDecimal rate = new BigDecimal(taxPercentage);
        final BigDecimal taxes = new RoundedDecimal(visitee.price.multiply(rate), "5", "100").value();
        final BigDecimal priceWithTaxes = visitee.priceWithTaxes.add(taxes);

        int scale = 2;
        return visitee
                .copyWith(change(Builder::withTaxes, taxes.add(visitee.taxes).setScale(scale, roundingMode)))
                .copyWith(change(Builder::withPriceWithTaxes, priceWithTaxes.setScale(scale, roundingMode)));
    }
}

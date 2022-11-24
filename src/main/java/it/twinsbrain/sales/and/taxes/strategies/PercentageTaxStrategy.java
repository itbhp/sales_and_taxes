package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import it.twinsbrain.sales.and.taxes.cart.CartItem.Builder;
import it.twinsbrain.sales.and.taxes.math.RoundedDecimal;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;

import static it.twinsbrain.sales.and.taxes.cart.CartItem.Change.change;

public abstract class PercentageTaxStrategy implements TaxStrategy {
    private final RoundingMode roundingMode = RoundingMode.CEILING;

    protected CartItem applyPercentageTax(final CartItem visitee, final String taxPercentage) {
        var rate = new BigDecimal(taxPercentage);
        var taxes = new RoundedDecimal(visitee.price.multiply(rate), "5", "100").value();
        final BiFunction<Builder, BigDecimal, Builder> taxesInCartItem = Builder::withTaxes;
        final BiFunction<Builder, BigDecimal, Builder> priceWithTaxesInCartItem = Builder::withPriceWithTaxes;
        int scale = 2;
        return visitee
                .copyWith(change(taxesInCartItem, roundedValue(scale, taxes.add(visitee.taxes))))
                .copyWith(change(priceWithTaxesInCartItem, roundedValue(scale, visitee.priceWithTaxes.add(taxes))));
    }

    private BigDecimal roundedValue(int scale, BigDecimal value) {
        return value.setScale(scale, roundingMode);
    }
}

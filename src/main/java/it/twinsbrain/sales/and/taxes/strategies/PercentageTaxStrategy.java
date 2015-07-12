package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author paolo
 */
public abstract class PercentageTaxStrategy implements TaxStrategy {
    private RoundingMode roundingMode = RoundingMode.CEILING;
    private int scale = 2;

    protected CartItem applyPercentageTax(CartItem visitee, String taxPercentage) {
        BigDecimal rate = visitee.taxPercentage.add(new BigDecimal(taxPercentage));
        BigDecimal taxes = visitee.price.multiply(rate);
        BigDecimal priceWithTaxes = visitee.price.add(taxes);


        return new CartItem.Builder()
                .withType(visitee.type)
                .withQuantity(visitee.quantity)
                .withPrice(visitee.price)
                .withDescription(visitee.description)
                .withTaxes(taxes.setScale(scale,roundingMode))
                .withPriceWithTaxes(priceWithTaxes.setScale(scale,roundingMode))
                .withTaxPercentage(rate.setScale(scale,roundingMode))
                .build();
    }


}

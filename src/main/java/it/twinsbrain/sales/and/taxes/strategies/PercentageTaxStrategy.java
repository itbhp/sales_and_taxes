package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import it.twinsbrain.sales.and.taxes.math.RoundedDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author paolo
 */
public abstract class PercentageTaxStrategy implements TaxStrategy {
    private RoundingMode roundingMode = RoundingMode.CEILING;
    private int scale = 2;

    protected CartItem applyPercentageTax(CartItem visitee, String taxPercentage) {
        BigDecimal rate = new BigDecimal(taxPercentage);
        BigDecimal taxes = new RoundedDecimal(visitee.price.multiply(rate), "5", "100").getValue();
        BigDecimal priceWithTaxes = visitee.priceWithTaxes.add(taxes);

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

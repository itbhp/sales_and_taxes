package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

import java.math.BigDecimal;

/**
 * @author paolo
 */
public abstract class PercentageTaxStrategy implements TaxStrategy {
        protected CartItem applyPercentageTax(CartItem visitee, String taxPercentage) {

            BigDecimal baseTaxes = visitee.priceWithTaxes.multiply(new BigDecimal(taxPercentage)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal taxes = baseTaxes.add(visitee.taxes).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal priceWithTaxes = visitee.price.add(taxes).setScale(2,BigDecimal.ROUND_HALF_UP);
            return new CartItem.Builder()
                    .withType(visitee.type)
                    .withQuantity(visitee.quantity)
                    .withPrice(visitee.price)
                    .withDescription(visitee.description)
                    .withTaxes(taxes)
                    .withPriceWithTaxes(priceWithTaxes)
                    .build();
        }
}

package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

import java.math.BigDecimal;

/**
 * @author paolo
 */
public class ImportedTaxStrategy implements TaxStrategy{
    @Override
    public CartItem updateItemWithTaxes(CartItem visitee) {
        BigDecimal originalPrice = visitee.price;
        BigDecimal baseTaxes = originalPrice.multiply(new BigDecimal("0.05")).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal taxes = baseTaxes.add(visitee.taxes).setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal priceWithTaxes = originalPrice.add(taxes).setScale(2,BigDecimal.ROUND_HALF_UP);
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

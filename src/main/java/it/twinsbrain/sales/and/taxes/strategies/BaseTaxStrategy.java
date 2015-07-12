package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

/**
 * @author paolo
 */
public class BaseTaxStrategy extends PercentageTaxStrategy {
    @Override
    public CartItem updateItemWithTaxes(CartItem visitee) {
        String taxPercentage = "0.1";
        return applyPercentageTax(visitee, taxPercentage);
    }

}

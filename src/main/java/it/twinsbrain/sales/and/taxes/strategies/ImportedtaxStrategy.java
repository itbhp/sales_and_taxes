package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

/**
 * @author paolo
 */
public class ImportedTaxStrategy extends PercentageTaxStrategy{
    @Override
    public CartItem updateItemWithTaxes(CartItem visitee) {
        String taxPercentage = "0.05";
        return applyPercentageTax(visitee, taxPercentage);
    }
}

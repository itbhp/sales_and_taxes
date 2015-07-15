package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

public class ImportedTaxStrategy extends PercentageTaxStrategy{
    @Override
    public CartItem updateTaxesOn(CartItem visitee) {
        String taxPercentage = "0.05";
        return applyPercentageTax(visitee, taxPercentage);
    }
}

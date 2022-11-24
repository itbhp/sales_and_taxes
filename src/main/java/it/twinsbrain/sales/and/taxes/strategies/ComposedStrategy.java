package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;

import java.util.List;

public class ComposedStrategy implements TaxStrategy {
    private final TaxStrategy current;
    private final TaxStrategy next;

    public ComposedStrategy(final TaxStrategy current, final TaxStrategy next) {
        this.current = current;
        this.next = next;
    }

    @Override
    public CartItem updateTaxesOn(final CartItem visitee) {
        return next.updateTaxesOn(current.updateTaxesOn(visitee));
    }

    protected List<TaxStrategy> strategies(){
        return List.of(current, next);
    }
}

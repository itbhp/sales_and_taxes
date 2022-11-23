package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import it.twinsbrain.sales.and.taxes.cart.ProductType;

import java.util.List;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.*;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class TaxStrategyFactory {

    private final List<ProductType> taxExcluded = List.of(BOOK, FOOD, MEDICAL);

    public TaxStrategy taxFor(final CartItem item) {
        if (item.isImported()) {
            if (taxExcluded.contains(item.type)) {
                return new ImportedTaxStrategy();
            } else {
                return new ComposedStrategy(new ImportedTaxStrategy(), new BaseTaxStrategy());
            }
        } else {
            if (taxExcluded.contains(item.type)) {
                return new NothingToDoStrategy();
            } else {
                return new BaseTaxStrategy();
            }
        }
    }
}

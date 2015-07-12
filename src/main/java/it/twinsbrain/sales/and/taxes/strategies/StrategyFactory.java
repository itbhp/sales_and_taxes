package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import it.twinsbrain.sales.and.taxes.cart.ProductType;

import java.util.List;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.*;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * @author paolo
 */
public class StrategyFactory {

    private static List<ProductType> taxExcluded = unmodifiableList(asList(BOOK, FOOD, MEDICAL));

    public static TaxStrategy createFrom(CartItem item){
        boolean isImported = item.description.contains("imported");
        if(isImported){
            if(taxExcluded.contains(item.type)){
                return new ImportedTaxStrategy();
            }else {
                return new ComposedStrategy(new ImportedTaxStrategy(),new BaseTaxStrategy());
            }
        }else{
            if(taxExcluded.contains(item.type)){
                return new NothingToDoStrategy();
            }else {
                return new BaseTaxStrategy();
            }
        }


    }
}

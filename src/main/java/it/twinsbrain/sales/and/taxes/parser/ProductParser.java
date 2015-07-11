package it.twinsbrain.sales.and.taxes.parser;

import it.twinsbrain.sales.and.taxes.cart.ProductType;

import java.math.BigDecimal;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.OTHERS;

/**
 *
 * @author paolo
 */
public class ProductParser {

    public int readQuantity(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[0]);
    }

    public ProductType readProductType(String input) {
        for (ProductType type : ProductType.values()) {
            if(type.match(input)){
                return type;
            }
        }
        return OTHERS;
    }

    public String readDescription(String input) {
        return "";
    }

    public BigDecimal readPrice(String input) {
        return BigDecimal.ZERO;
    }
}

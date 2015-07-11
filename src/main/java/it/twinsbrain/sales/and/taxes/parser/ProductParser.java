package it.twinsbrain.sales.and.taxes.parser;

import it.twinsbrain.sales.and.taxes.cart.ProductType;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.OTHERS;

/**
 *
 * @author paolo
 */
public class ProductParser {

    private String inputPattern = "(\\d+)\\s([\\w\\s]+)\\sat\\s(\\d+\\.\\d+)";

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
        return extractGroupFrom(input, 2);
    }

    public BigDecimal readPrice(String input) {
        return new BigDecimal(extractGroupFrom(input, 3));
    }

    private String extractGroupFrom(String input, int groupNumber) {
        Pattern p = Pattern.compile(inputPattern);
        Matcher m = p.matcher(input);
        m.find();
        return m.group(groupNumber);
    }
}

package it.twinsbrain.sales.and.taxes.parser;

import it.twinsbrain.sales.and.taxes.cart.ProductType;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.OTHERS;

public class CartItemParser {

    private final String regexPattern = "(\\d+)\\s([\\w\\s]+)\\sat\\s(\\d+\\.\\d+)";
    private final Pattern inputPattern;

    public CartItemParser(){
        inputPattern = Pattern.compile(regexPattern);
    }

    public int readQuantity(final String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[0]);
    }

    public ProductType readProductType(final String input) {
        for (ProductType type : ProductType.values()) {
            if(type.match(input)){
                return type;
            }
        }
        return OTHERS;
    }

    public String readDescription(final String input) {
        return extractGroupFrom(input, 2);
    }

    public BigDecimal readPrice(final String input) {
        return new BigDecimal(extractGroupFrom(input, 3));
    }

    private String extractGroupFrom(final String input, final int groupNumber) {
        Matcher m = inputPattern.matcher(input);
        m.find();
        return m.group(groupNumber);
    }
}

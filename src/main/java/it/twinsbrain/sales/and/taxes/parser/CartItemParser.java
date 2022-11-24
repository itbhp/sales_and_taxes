package it.twinsbrain.sales.and.taxes.parser;

import it.twinsbrain.sales.and.taxes.cart.ProductType;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.OTHERS;

public class CartItemParser {

    public static final int DESCRIPTION = 2;
    public static final int PRICE = 3;
    private final Pattern inputPattern;

    public CartItemParser(){
        String regexPattern = "(\\d+)\\s([\\w\\s]+)\\sat\\s(\\d+\\.\\d+)";
        inputPattern = Pattern.compile(regexPattern);
    }

    public int readQuantity(final String input) {
        var parts = input.split(" ");
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
        return read(input, DESCRIPTION);
    }

    public BigDecimal readPrice(final String input) {
        return new BigDecimal(read(input, PRICE));
    }

    private String read(final String input, final int groupNumber) {
        var matcher = inputPattern.matcher(input);
        matcher.find();
        return matcher.group(groupNumber);
    }
}

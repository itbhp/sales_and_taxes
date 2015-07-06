package it.twinsbrain.sales.and.taxes.parser;

import static it.twinsbrain.sales.and.taxes.parser.ProductParser.ProdcutType.BOOK;
import static it.twinsbrain.sales.and.taxes.parser.ProductParser.ProdcutType.FOOD;
import static it.twinsbrain.sales.and.taxes.parser.ProductParser.ProdcutType.MEDICAL;
import static it.twinsbrain.sales.and.taxes.parser.ProductParser.ProdcutType.MUSIC;
import static it.twinsbrain.sales.and.taxes.parser.ProductParser.ProdcutType.OTHERS;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author paolo
 */
public class ProductParser {

    public int readQuantity(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[0]);
    }

    public ProdcutType readProdcutType(String input) {
        for (ProdcutType type : ProdcutType.values()) {
            if(type.match(input)){
                return type;
            }
        }
        return OTHERS;
    }

    public static enum ProdcutType {

        FOOD(asList("chocolate", "bread")),
        BOOK(asList("book")),
        MUSIC(asList("cd", "mp3")),
        MEDICAL(asList("pills")),
        OTHERS(Collections.<String>emptyList());

        ProdcutType(List<String> keywords) {
            this.keywords = keywords;
        }

        private final List<String> keywords;

        public boolean match(String productDescription) {
            return keywords.stream().anyMatch((keyword) -> (productDescription.contains(keyword)));
        }

    }

}

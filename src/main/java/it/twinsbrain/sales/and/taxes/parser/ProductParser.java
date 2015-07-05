package it.twinsbrain.sales.and.taxes.parser;

import static it.twinsbrain.sales.and.taxes.parser.ProductParser.ProdcutType.BOOK;
import static it.twinsbrain.sales.and.taxes.parser.ProductParser.ProdcutType.FOOD;
import static it.twinsbrain.sales.and.taxes.parser.ProductParser.ProdcutType.MEDICAL;
import static it.twinsbrain.sales.and.taxes.parser.ProductParser.ProdcutType.MUSIC;
import static it.twinsbrain.sales.and.taxes.parser.ProductParser.ProdcutType.OTHERS;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.Collections;
import static java.util.Collections.emptyList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author paolo
 */
public class ProductParser {

    private final EnumMap<ProdcutType, List<String>> descriptionsByProduct;

    public ProductParser() {
        descriptionsByProduct = new EnumMap<>(ProdcutType.class);
        descriptionsByProduct.put(FOOD, asList("chocolate", "bread"));
        descriptionsByProduct.put(BOOK, asList("book"));
        descriptionsByProduct.put(MEDICAL, asList("pills"));
        descriptionsByProduct.put(MUSIC, asList("cd","mp3"));
        descriptionsByProduct.put(OTHERS, emptyList());

    }

    public int readQuantity(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[0]);
    }

    public ProdcutType readProdcutType(String input) {
        for(Map.Entry<ProdcutType,List<String>> entry: descriptionsByProduct.entrySet()){
            for(String description: entry.getValue()){
                if(input.toLowerCase().contains(description)){
                    return entry.getKey();
                }
            }
        }
        return ProdcutType.OTHERS;
    }

    public static enum ProdcutType {

        FOOD, BOOK, MUSIC, MEDICAL, OTHERS;

    }

}

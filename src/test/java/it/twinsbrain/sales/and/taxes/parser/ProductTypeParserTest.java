package it.twinsbrain.sales.and.taxes.parser;

import static it.twinsbrain.sales.and.taxes.parser.ProductParser.ProdcutType.FOOD;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author paolo
 */
public class ProductTypeParserTest {
    
    private ProductParser underTest;
    
    private String input;
    private ProductParser.ProdcutType readProdcutType;
    
    @Before
    public void setup(){
        underTest = new ProductParser();
    }
    
    @Test
    public void chocolate_is_food(){
        given:{
            input = "1 chocolate bar: 0.85";
        }
        when:{
            readProdcutType = underTest.readProdcutType(input);
        }
        then:{
            assertThat(readProdcutType, is(FOOD));
        }
    }
    
    @Test
    public void bread_is_food(){
        given:{
            input = "1 bread pack 20 slices: 2.85";
        }
        when:{
            readProdcutType = underTest.readProdcutType(input);
        }
        then:{
            assertThat(readProdcutType, is(FOOD));
        }
    }

}

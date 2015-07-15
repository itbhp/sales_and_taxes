package it.twinsbrain.sales.and.taxes.parser;

import it.twinsbrain.sales.and.taxes.cart.ProductType;
import org.junit.Before;
import org.junit.Test;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProductTypeParserTest {
    
    private CartItemParser underTest;
    
    private String input;
    private ProductType readProductType;
    
    @Before
    public void setup(){
        underTest = new CartItemParser();
    }
    
    @Test
    public void chocolate_is_food(){
        given:{
            input = "1 chocolate bar at 0.85";
        }
        when:{
            readProductType = underTest.readProductType(input);
        }
        then:{
            assertThat(readProductType, is(FOOD));
        }
    }
    
    @Test
    public void bread_is_food(){
        given:{
            input = "1 bread pack 20 slices at 2.85";
        }
        when:{
            readProductType = underTest.readProductType(input);
        }
        then:{
            assertThat(readProductType, is(FOOD));
        }
    }

    @Test
    public void pills_are_medicine(){
        given:{
            input = "1 bottle of pills at 2.85";
        }
        when:{
            readProductType = underTest.readProductType(input);
        }
        then:{
            assertThat(readProductType, is(MEDICAL));
        }
    }

    @Test
    public void any_book_is_a_book(){
        given:{
            input = "1 Moccia's book at 0.00";
        }
        when:{
            readProductType = underTest.readProductType(input);
        }
        then:{
            assertThat(readProductType, is(BOOK));
        }
    }

    @Test
    public void a_cd_is_music(){
        given:{
            input = "1 AC/DC the gold collection CD at 20.00";
        }
        when:{
            readProductType = underTest.readProductType(input);
        }
        then:{
            assertThat(readProductType, is(MUSIC));
        }
    }

    @Test
    public void an_mp3_is_music(){
        given:{
            input = "1 Dire Straits - Walk of Life mp3 at 0.99";
        }
        when:{
            readProductType = underTest.readProductType(input);
        }
        then:{
            assertThat(readProductType, is(MUSIC));
        }
    }

    @Test
    public void anythingelse_is_others(){
        given:{
            input = "1 imported bottle of perfume at 27.99";
        }
        when:{
            readProductType = underTest.readProductType(input);
        }
        then:{
            assertThat(readProductType, is(OTHERS));
        }
    }

}

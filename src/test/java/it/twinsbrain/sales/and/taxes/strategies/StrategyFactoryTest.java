package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.MEDICAL;
import static it.twinsbrain.sales.and.taxes.cart.ProductType.MUSIC;
import static it.twinsbrain.sales.and.taxes.cart.ProductType.OTHERS;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StrategyFactoryTest {

    private TaxStrategyFactory underTest;
    private CartItem item;
    private TaxStrategy strategy;

    @Before
    public void setup() {
        underTest = new TaxStrategyFactory();
    }

    @Test
    public void medical_not_imported_are_tax_excluded() {
        given:
        {
            item = new CartItem.Builder().withDescription("bottle of pills for headache").withType(MEDICAL).build();
        }
        when:
        {
            strategy = underTest.createFor(item);
        }
        then:
        {
            assertThat(strategy, is(instanceOf(NothingToDoStrategy.class)));
        }
    }

    @Test
    public void medical_imported_have_only_import_taxes() {
        given:
        {
            item = new CartItem.Builder().withDescription("imported bottle of pills for headache").withType(MEDICAL).build();
        }
        when:
        {
            strategy = underTest.createFor(item);
        }
        then:
        {
            assertThat(strategy, is(instanceOf(ImportedTaxStrategy.class)));
        }
    }

    @Test
    public void cd_not_imported_have_only_base_taxes() {
        given:
        {
            item = new CartItem.Builder().withDescription("Queen compilation cd").withType(MUSIC).build();
        }
        when:
        {
            strategy = underTest.createFor(item);
        }
        then:
        {
            assertThat(strategy, is(instanceOf(BaseTaxStrategy.class)));
        }
    }

    @Test
    public void cd_imported_have_base_and_imported_taxes() throws NoSuchFieldException, IllegalAccessException {
        given:
        {
            item = new CartItem.Builder().withDescription("imported Queen compilation cd").withType(MUSIC).withPrice(new BigDecimal("1.00")).build();
        }
        when:
        {
            strategy = underTest.createFor(item);
        }
        then:
        {
            assertThat(strategy, is(instanceOf(ComposedStrategy.class)));

            ComposedStrategy toVerify = (ComposedStrategy) strategy;

            assertThat(toVerify.strategies(), containsInstanceOf(ImportedTaxStrategy.class));
            assertThat(toVerify.strategies(), containsInstanceOf(BaseTaxStrategy.class));
        }
    }

    @Test
    public void imported_bottle_of_perfume_have_base_and_imported_taxes() throws NoSuchFieldException, IllegalAccessException {
        given:
        {
            item = new CartItem.Builder().withDescription("imported bottle of perfume").withType(OTHERS).withPrice(new BigDecimal("27.99")).build();
        }
        when:
        {
            strategy = underTest.createFor(item);
        }
        then:
        {
            assertThat(strategy, is(instanceOf(ComposedStrategy.class)));

            ComposedStrategy toVerify = (ComposedStrategy) strategy;

            assertThat(toVerify.strategies(), containsInstanceOf(ImportedTaxStrategy.class));
            assertThat(toVerify.strategies(), containsInstanceOf(BaseTaxStrategy.class));
        }
    }

    private BaseMatcher<List<TaxStrategy>> containsInstanceOf(final Class<? extends TaxStrategy> clazz)
    {
     return new BaseMatcher<List<TaxStrategy>>() {
         @Override
         public boolean matches(Object item) {
             List<TaxStrategy> list = (List<TaxStrategy>)item;
             for(TaxStrategy strategy: list){
                 if(clazz.isInstance(strategy)){
                     return true;
                 }
             }
             return false;
         }

         @Override
         public void describeTo(Description description) {
            description.appendText("the list does not contains instance of "+clazz);
         }
     };
    }
}

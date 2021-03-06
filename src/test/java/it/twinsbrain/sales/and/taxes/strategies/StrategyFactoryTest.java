package it.twinsbrain.sales.and.taxes.strategies;

import it.twinsbrain.sales.and.taxes.cart.CartItem;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static it.twinsbrain.sales.and.taxes.cart.ProductType.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class StrategyFactoryTest {

    private TaxStrategyFactory underTest;
    private CartItem item;
    private TaxStrategy strategy;

    @Before
    public void setUp() {
        underTest = new TaxStrategyFactory();
    }

    @Test
    public void medicalNotImportedAreTaxExcluded() {
        item = new CartItem.Builder().withDescription("bottle of pills for headache").withType(MEDICAL).build();

        strategy = underTest.taxFor(item);

        assertThat(strategy, is(instanceOf(NothingToDoStrategy.class)));
    }

    @Test
    public void medicalImportedHaveOnlyImportTaxes() {
        item = new CartItem.Builder().withDescription("imported bottle of pills for headache")
                .withType(MEDICAL).build();

        strategy = underTest.taxFor(item);

        assertThat(strategy, is(instanceOf(ImportedTaxStrategy.class)));
    }

    @Test
    public void cdNotImportedHaveOnlyBaseTaxes() {
        item = new CartItem.Builder().withDescription("Queen compilation cd").withType(MUSIC).build();

        strategy = underTest.taxFor(item);

        assertThat(strategy, is(instanceOf(BaseTaxStrategy.class)));
    }

    @Test
    public void cdImportedHaveBaseAndImportedTaxes() throws NoSuchFieldException, IllegalAccessException {
        item = new CartItem.Builder().withDescription("imported Queen compilation cd").withType(MUSIC)
                .withPrice(new BigDecimal("1.00")).build();

        strategy = underTest.taxFor(item);

        assertThat(strategy, is(instanceOf(ComposedStrategy.class)));
        ComposedStrategy toVerify = (ComposedStrategy) strategy;
        assertThat(toVerify.strategies(), hasItem(instanceOf(ImportedTaxStrategy.class)));
        assertThat(toVerify.strategies(), hasItem(instanceOf(BaseTaxStrategy.class)));
    }

    @Test
    public void importedBottleOfPerfumeHaveBaseAndImportedTaxes()
            throws NoSuchFieldException, IllegalAccessException {
        item = new CartItem.Builder().withDescription("imported bottle of perfume").withType(OTHERS)
                .withPrice(new BigDecimal("27.99")).build();

        strategy = underTest.taxFor(item);

        assertThat(strategy, is(instanceOf(ComposedStrategy.class)));
        ComposedStrategy toVerify = (ComposedStrategy) strategy;
        assertThat(toVerify.strategies(), hasItem(instanceOf(ImportedTaxStrategy.class)));
        assertThat(toVerify.strategies(), hasItem(instanceOf(BaseTaxStrategy.class)));
    }

}

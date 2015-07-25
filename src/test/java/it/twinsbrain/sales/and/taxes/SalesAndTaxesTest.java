package it.twinsbrain.sales.and.taxes;


import it.twinsbrain.sales.and.taxes.cart.ShoppingCart;
import it.twinsbrain.sales.and.taxes.cart.ShoppingCartBuilder;
import it.twinsbrain.sales.and.taxes.parser.CartItemParser;
import it.twinsbrain.sales.and.taxes.strategies.TaxStrategyFactory;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * SALES TAXES
 * <p>
 * Basic sales tax is applicable at a rate of 10% on all goods, except books, food,
 * and medical products that are exempt. Import duty is an additional sales tax
 * applicable on all imported goods at a rate of 5%, with no exemptions.
 * <p>
 * When I purchase items I receive a receipt which lists the name of all the items
 * and their price (including tax), finishing with the total cost of the items,
 * and the total amounts of sales taxes paid.  The rounding rules for sales tax are
 * that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to
 * the nearest 0.05) amount of sales tax.
 * <p>
 * Write an application that prints out the receipt details for these shopping baskets...
 * INPUT:
 * Input 1:
 * 1 book at 12.49
 * 1 music CD at 14.99
 * 1 chocolate bar at 0.85
 * <p>
 * Input 2:
 * 1 imported box of chocolates at 10.00
 * 1 imported bottle of perfume at 47.50
 * <p>
 * Input 3:
 * 1 imported bottle of perfume at 27.99
 * 1 bottle of perfume at 18.99
 * 1 packet of headache pills at 9.75
 * 1 imported box of chocolates at 11.25
 * <p>
 * OUTPUT
 * Output 1:
 * 1 book: 12.49
 * 1 music CD: 16.49
 * 1 chocolate bar: 0.85
 * Sales Taxes: 1.50
 * Total: 29.83
 * <p>
 * Output 2:
 * 1 imported box of chocolates: 10.50
 * 1 imported bottle of perfume: 54.65
 * Sales Taxes: 7.65
 * Total: 65.15
 * <p>
 * Output 3:
 * 1 imported bottle of perfume: 32.19
 * 1 bottle of perfume: 20.89
 * 1 packet of headache pills: 9.75
 * 1 imported box of chocolates: 11.85
 * Sales Taxes: 6.70
 * Total: 74.68
 */
public class SalesAndTaxesTest {

    ShoppingCart underTest;
    private String output;

    @Test
    public void acceptanceTestOne() throws IOException, URISyntaxException {
        given:
        {
            underTest = new ShoppingCartBuilder(new CartItemParser(), new TaxStrategyFactory())
                    .createShoppingCartFrom(readTextFile("/inputs/one.txt"));
        }
        when:
        {
            output = underTest.toReceipt().print();
        }
        then:
        {
            assertThat(output, is(equalTo(readTextFile("/outputs/one.txt"))));
        }
    }

    @Test
    public void acceptanceTestTwo() throws IOException, URISyntaxException {
        given:
        {
            underTest = new ShoppingCartBuilder(new CartItemParser(), new TaxStrategyFactory())
                    .createShoppingCartFrom(readTextFile("/inputs/two.txt"));
        }
        when:
        {
            output = underTest.toReceipt().print();
        }
        then:
        {
            assertThat(output, is(equalTo(readTextFile("/outputs/two.txt"))));
        }
    }

    @Test
    public void acceptanceTestThree() throws IOException, URISyntaxException {
        given:
        {
            underTest = new ShoppingCartBuilder(new CartItemParser(), new TaxStrategyFactory())
                    .createShoppingCartFrom(readTextFile("/inputs/three.txt"));
        }
        when:
        {
            output = underTest.toReceipt().print();
        }
        then:
        {
            assertThat(output, is(equalTo(readTextFile("/outputs/three.txt"))));
        }
    }

    private String readTextFile(String path) throws IOException, URISyntaxException {
        Path resolvedPath = Paths.get(this.getClass().getResource(path).toURI());
        return new String(Files.readAllBytes(resolvedPath), "UTF-8");
    }

}

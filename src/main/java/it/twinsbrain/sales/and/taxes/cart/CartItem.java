package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.strategies.TaxStrategy;

import java.math.BigDecimal;

public class CartItem {

    public final ProductType type;
    public final int quantity;
    public final BigDecimal price;
    public final String description;
    public final BigDecimal taxes;
    public final BigDecimal taxPercentage;
    public final BigDecimal priceWithTaxes;

    private CartItem(final ProductType type, final int quantity, final BigDecimal price,
                     final String description, final BigDecimal taxes, final BigDecimal taxPercentage,
                     final BigDecimal priceWithTaxes) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.taxes = taxes;
        this.taxPercentage = taxPercentage;
        this.priceWithTaxes = priceWithTaxes;
    }

    public CartItem accept(final TaxStrategy visitor) {
        return visitor.updateTaxesOn(this);
    }

    @Override
    public String toString() {
        return quantity + " " + description + ": " + priceWithTaxes;
    }

    public static class Builder {
        private ProductType type;
        private int quantity = 0;
        private BigDecimal price = BigDecimal.ZERO;
        private String description;
        private BigDecimal taxes = BigDecimal.ZERO;
        private BigDecimal taxPercentage = BigDecimal.ZERO;
        private BigDecimal priceWithTaxes = BigDecimal.ZERO;

        public Builder withType(final ProductType type) {
            this.type = type;
            return this;
        }

        public Builder withQuantity(final int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withPrice(final BigDecimal price) {
            this.price = price;
            this.priceWithTaxes = price;
            return this;
        }

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withTaxes(final BigDecimal taxes) {
            this.taxes = taxes;
            return this;
        }

        public Builder withTaxPercentage(final BigDecimal taxPercentage) {
            this.taxPercentage = taxPercentage;
            return this;
        }

        public Builder withPriceWithTaxes(final BigDecimal price) {
            this.priceWithTaxes = price;
            return this;
        }

        public CartItem build() {
            return new CartItem(type, quantity, price, description, taxes, taxPercentage, priceWithTaxes);
        }
    }

}

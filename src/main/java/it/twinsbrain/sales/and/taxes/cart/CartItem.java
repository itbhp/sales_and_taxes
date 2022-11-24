package it.twinsbrain.sales.and.taxes.cart;

import it.twinsbrain.sales.and.taxes.strategies.TaxStrategy;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class CartItem {

    public final ProductType type;
    public final int quantity;
    public final BigDecimal price;
    public final String description;
    public final BigDecimal taxes;
    public final BigDecimal priceWithTaxes;

    private CartItem(
            final ProductType type,
            final int quantity,
            final BigDecimal price,
            final String description,
            final BigDecimal taxes,
            final BigDecimal priceWithTaxes
    ) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.taxes = taxes;
        this.priceWithTaxes = priceWithTaxes;
    }

    public boolean isImported() {
        return description.toLowerCase().contains("imported");
    }

    public CartItem accept(final TaxStrategy visitor) {
        return visitor.updateTaxesOn(this);
    }

    public <T> CartItem copyWith(Change<T> change){
        var builder = Builder.from(this);
        return change.applyChangeTo(builder).build();
    }

    @Override
    public String toString() {
        return quantity + " " + description + ": " + priceWithTaxes;
    }

    public static class Change<T> {
        private final BiFunction<Builder, T, Builder> modifier;
        private final T newValue;


        public static <T> Change<T> change(BiFunction<Builder, T, Builder> modifier, T newValue) {
            return new Change<>(modifier, newValue);
        }
        public Change(BiFunction<Builder, T, Builder> modifier, T newValue) {
            this.modifier = modifier;
            this.newValue = newValue;
        }

        public Builder applyChangeTo(Builder builder){
            return modifier.apply(builder, newValue);
        }
    }

    public static class Builder {
        private ProductType type;
        private int quantity = 0;
        private BigDecimal price = BigDecimal.ZERO;
        private String description;
        private BigDecimal taxes = BigDecimal.ZERO;
        private BigDecimal priceWithTaxes = BigDecimal.ZERO;

        public static Builder from(CartItem previous){
            return new CartItem.Builder()
                    .withType(previous.type)
                    .withQuantity(previous.quantity)
                    .withPrice(previous.price)
                    .withDescription(previous.description)
                    .withTaxes(previous.taxes)
                    .withPriceWithTaxes(previous.priceWithTaxes);
        }

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

        public Builder withPriceWithTaxes(final BigDecimal price) {
            this.priceWithTaxes = price;
            return this;
        }

        public CartItem build() {
            return new CartItem(type, quantity, price, description, taxes, priceWithTaxes);
        }
    }

}

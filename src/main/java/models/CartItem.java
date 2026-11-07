package models;

import java.math.BigDecimal;

public record CartItem(String name, String description, BigDecimal price, int quantity) {
    public CartItem {
        price = price.stripTrailingZeros();
    }
}

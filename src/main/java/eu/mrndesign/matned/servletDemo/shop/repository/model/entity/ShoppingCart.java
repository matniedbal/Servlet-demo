package eu.mrndesign.matned.servletDemo.shop.repository.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import lombok.Getter;

@Getter
public class ShoppingCart {

    private final List<ProductItem> productItems;

    private Integer totalPrice;

    public ShoppingCart() {
        productItems = new ArrayList<>();
        totalPrice = 0;
    }

    public void addToShoppingCart(Product p, Integer quantity) {
        productItems.stream()
                .filter(pi -> pi.getProduct().getId() == p.getId())
                .findFirst()
                .ifPresentOrElse(
                        (pi) -> pi.increaseQuantity(quantity),
                        () -> {
                            int productItemId = productItems.size() + 1;
                            ProductItem item = new ProductItem(productItemId, p, quantity);
                            productItems.add(item);
                        });
        int productCost = p.getPrice() * quantity;
        totalPrice = totalPrice + productCost;
    }

    public void increaseQuantity(int prodItemId) {
        productItems.stream()
                .filter(pi -> pi.getId() == prodItemId)
                .findFirst()
                .ifPresent(productItem -> productItem.setQuantity(productItem.getQuantity() + 1));
    }

    public void decreaseQuantity(int prodItemId) {
        productItems.stream()
                .filter(pi -> pi.getId() == prodItemId)
                .findFirst()
                .ifPresent(productItem -> {
                    productItem.setQuantity(productItem.getQuantity() - 1);
                    if (productItem.getQuantity() <= 0) productItems.remove(productItem);
                });
    }

    public Product getProductById(int itemId) {
        AtomicReference<Product> product = new AtomicReference<>();
        productItems.stream()
                .filter(p -> p.getId() == itemId)
                .findFirst()
                .map(ProductItem::getProduct)
                .ifPresentOrElse(product::set, () -> {
                });
        return product.get();
    }

    public int getQuantity(int itemId) {
        AtomicInteger amount = new AtomicInteger();
        productItems.stream()
                .filter((p)-> p.getId() == itemId)
                .findFirst()
                .map(ProductItem::getQuantity)
                .ifPresent(amount::set);
        return amount.get();
    }

    public ProductItem findProductItemByProductId(int prodItemId) {
        AtomicReference<ProductItem> productItem = new AtomicReference<>();
        productItems.stream()
                .filter(p-> p.getProduct().getId() == prodItemId)
                .findFirst()
                .ifPresent(productItem::set);
        return productItem.get();
    }

    public void updateTotalPrice() {
        totalPrice = productItems.stream()
                .mapToInt(p -> p.getProduct().getPrice() * p.getQuantity())
                .reduce(0, Integer::sum);
    }
}

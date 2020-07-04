package eu.mrndesign.matned.servletDemo.shop.repository;

import eu.mrndesign.matned.servletDemo.shop.repository.model.ProductDao;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.ProductItem;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.ShoppingCart;
import lombok.Getter;

import java.util.*;

public class ProductRepository {

    private static ProductRepository repository;

    public static ProductRepository getInstance() {
        if (repository == null) {
            repository = new ProductRepository();
        }
        return repository;
    }

    @Getter
    private final List<Product> products;
    private final ProductDao dao;

    private ProductRepository() {
        products = new ArrayList<>();
        dao = new ProductDao();
    }

    public void save(Product product) {
        product.setId(products.size() + 1);
        products.add(product);
        dao.add(product);
    }

    public void search(String item , String searchedBy){
        dao.setSearch(item);
        dao.setSearchedBy(searchedBy);
        findAll();
    }

    public List<Product> findAll() {
        products.clear();
        products.addAll(dao.findAll());
        return products;
    }

    public Product findById(int id) {
        Optional<Product> product = products.stream()
                .filter(p -> p.getId() == id)
                .findAny();
        return product.orElse(null);
    }

    public void deleteProd(Integer id) {
        Optional<Product> product = products.stream()
                .filter(p -> p.getId() == id)
                .findAny();
        product.ifPresent(products::remove);
        product.ifPresent(dao::remove);
    }

    public void sort(String sortBy) {
        dao.sort(sortBy);
    }

    public void setProduct(Product productToEdit) {
        dao.update(productToEdit);
    }

    public void setPage(int page, int maxResults){
        dao.setPage(page,maxResults);
    }

    public void clearProductList() {
        products.clear();
    }

    public int getLastPage(int maxResults) {
        int records = dao.getNumberOfRecords();
        return records/maxResults;
    }
    public int getMaxPrice() {
        return dao.getMax("price");
    }

    public int getMaxQuantity() {
        return dao.getMax("quantity");
    }

    public void setNumberCriteria(int minPrice, int maxPrice, int minQuantity, int maxQuantity) {
        dao.setNumberCriteria(minPrice,maxPrice,minQuantity,maxQuantity);
    }
    public void setCategories(String category) {
        dao.setCategories(category);
    }

    public void actualizeProductList_WithProductsInShoppingCart(ShoppingCart cart) {
        for (Product el : products) {
            for (ProductItem cartEl :
                    cart.getProductItems()) {
                if(el.getId() == cartEl.getProduct().getId())
                {
                    int actualQuantity = el.getQuantity() - cartEl.getQuantity();
                    if(actualQuantity < 0) {
                        cartEl.setQuantity(cartEl.getQuantity()+actualQuantity);
                        actualizeProductList_WithProductsInShoppingCart(cart);
                    }
                    el.setQuantity(actualQuantity);
                }
            }
        }
    }

    public int getAbsoluteQuantity(Product product) {
        return dao.getAbsoluteQuantity(product);
    }
}

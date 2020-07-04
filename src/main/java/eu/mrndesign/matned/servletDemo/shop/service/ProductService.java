package eu.mrndesign.matned.servletDemo.shop.service;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;
import eu.mrndesign.matned.servletDemo.shop.repository.ProductRepository;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.ShoppingCart;

import java.util.*;

public class ProductService {


    private static ProductService instance = null;

    public static ProductService getInstance(){
        if(instance == null){
            instance = new ProductService();
        }
        return instance;
    }

    private final ProductRepository repo;

    private ProductService() {
        repo = ProductRepository.getInstance();
    }

    public void saveProduct(Product product){
        repo.save(product);
    }

    public List<Product> findAll(){
        return repo.findAll();
    }


    public void search(String item, String searchBy){
        repo.search(item, searchBy);
    }

    public void deleteProduct(Integer prodId) {
        repo.deleteProd(prodId);
    }

    public Product findById(int id) {
        return repo.findById(id);
    }

    public void setProduct(Product productToEdit) {
        repo.setProduct(productToEdit);
    }

    public void setPage(int page, int maxResults){
        repo.setPage(page,maxResults);
    }

    public void sort(String sorting) {
        repo.sort(sorting);
    }

    public int getLastPage(int maxResults) {
        return repo.getLastPage(maxResults);
    }

    public int getMaxPrice() {
        return repo.getMaxPrice();
    }

    public int getMaxQuantity() {
        return repo.getMaxQuantity();
    }

    public void setNumberCriteria(int minPrice, int maxPrice, int minQuantity, int maxQuantity) {
        repo.setNumberCriteria(minPrice,maxPrice,minQuantity,maxQuantity);
    }
    public void setCategories(String category) {
        repo.setCategories(category);
    }

    public void actualizeProductList_WithProductsInShoppingCart(ShoppingCart cart) {
        repo.actualizeProductList_WithProductsInShoppingCart(cart);
    }

    public int getAbsoluteQuantity(Product product) {
        return repo.getAbsoluteQuantity(product);
    }
}



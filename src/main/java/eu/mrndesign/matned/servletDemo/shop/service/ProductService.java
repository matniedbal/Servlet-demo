package eu.mrndesign.matned.servletDemo.shop.service;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;
import eu.mrndesign.matned.servletDemo.shop.repository.ProductRepository;

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
    private boolean isSearched;
    private String sortedBy;

    private ProductService() {
        sortedBy = "id";
        repo = ProductRepository.getInstance();
    }

    public void saveProduct(Product product){
        repo.save(product);
        //sending email to admin that the product has been posted
    }

    public List<Product> findAll(){
        if(repo.getProducts().isEmpty()) {
            return repo.initiateListFromDB_Page();
        }
        else return repo.findAll();
    }


    public List<Product> search(String item, String searchBy){
        return repo.search(item, searchBy);
    }


    public void deleteProduct(Integer prodId) {
        repo.deleteProd(prodId);
    }

    public void sortProducts(String sortBy) {
        sortedBy = sortBy;
        repo.sortBy(sortedBy);
    }

    public Product findById(int id) {
        return repo.findById(id);
    }

    public void setProduct(Product productToEdit) {
        repo.setProduct(productToEdit);
    }


}



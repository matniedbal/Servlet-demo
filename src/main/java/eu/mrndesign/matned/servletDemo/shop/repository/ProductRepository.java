package eu.mrndesign.matned.servletDemo.shop.repository;

import eu.mrndesign.matned.servletDemo.shop.repository.model.ProductDao;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepository {

    private static ProductRepository repository;

    public static ProductRepository getInstance() {
        if (repository == null) {
            repository = new ProductRepository();
        }
        return repository;
    }

    @Getter
    private List<Product> products;
    private ProductDao dao;

    private ProductRepository() {
        products = new ArrayList<>();
    }

    public void save(Product product) {
        product.setId(products.size() + 1);
        products.add(product);
    }

    public List<Product> search(String item , String searchedBy){
        dao = new ProductDao();
        dao.setSearch(item);
        dao.setSearchedBy(searchedBy);
        products = dao.findAll();
        return products;
    }

    public List<Product> initiateListFromDB_Page(){
        dao = new ProductDao();
        products = dao.findAll();
        return products;
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById(int id) {
        Optional<Product> product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findAny();
        return product.orElse(null);
    }

    public void deleteProd(Integer id) {
        Optional<Product> product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findAny();
        product.ifPresent(products::remove);
    }

    public void sortBy(String sortBy) {
        switch (sortBy) {
            case "id": {
                sortStatement(Comparator.comparing(Product::getId));
                break;
            }
            case "name": {
                sortStatement(Comparator.comparing(Product::getName));
                break;
            }
            case "category": {
                sortStatement(Comparator.comparing(Product::getCategory));
                break;
            }
            case "description": {
                sortStatement(Comparator.comparing(Product::getDescription));
                break;
            }
            case "price": {
                sortStatement(Comparator.comparing(Product::getPrice));
                break;
            }
            case "quantity": {
                sortStatement(Comparator.comparing(Product::getQuantity));
                break;
            }
            default: products.sort(Comparator.comparing(Product::getId));
        }
    }

    public void setProduct(Product productToEdit) {
        int prodId = productToEdit.getId();
        products.get(prodId).setName(productToEdit.getName());
        products.get(prodId).setDescription(productToEdit.getDescription());
        products.get(prodId).setCategory(productToEdit.getCategory());
        products.get(prodId).setPrice(productToEdit.getPrice());
        products.get(prodId).setQuantity(productToEdit.getQuantity());
        dao.update(products.get(prodId));
    }


    private void sortStatement(Comparator<Product> comparing) {
        if (!isSorted(products.stream().sorted(comparing).collect(Collectors.toList())))
            products.sort(comparing);
        else products.sort(comparing.reversed());
    }

    private boolean isSorted(List<Product> collect) {
        for (int i = 0; i < products.size(); i++) {
            if (!products.get(i).equals(collect.get(i))) return false;
        }
        return true;
    }

}

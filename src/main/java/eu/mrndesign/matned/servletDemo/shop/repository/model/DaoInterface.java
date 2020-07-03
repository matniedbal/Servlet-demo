package eu.mrndesign.matned.servletDemo.shop.repository.model;

import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;

import java.util.List;

public interface DaoInterface<A>{
    List<A> findAll();
    void add(A a);
    void update(A a);
    void remove(A a);
    void sort(String sortBy);
    int getNumberOfRecords();
    int getMax(String column);
}

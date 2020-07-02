package eu.mrndesign.matned.servletDemo.shop.repository.model;

import java.util.List;

public interface DaoInterface<A>{
    List<A> findAll();
    void add(A a);
    void update(A a1);
}

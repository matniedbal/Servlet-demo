package eu.mrndesign.matned.servletDemo.shop.repository.model;

public interface DaoInterface<A> extends MainDaoInterface<A>{

    void sort(String sortBy);
    int getNumberOfRecords();
    int getMax(String column);
    int getAbsoluteQuantity(A a);
}

package eu.mrndesign.matned.servletDemo.autoMoto;

import java.util.List;

public interface DaoInterface <A>{
    public List<A> find();
    public void add(A a);
}

package eu.mrndesign.matned.servletDemo.shop.repository.model;

import eu.mrndesign.matned.servletDemo.shop.repository.hibernate.HibernateUtil;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Category;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements DaoInterface<Product> {

    private static final String DEFAULT_SEARCH_METHOD_BY = "name";
    private static final String EMPTY_STRING_INITIALIZER = "";
    private static final String SORTING_DEFAULT_METHOD_BY = "id";

    private String searchedBy;
    private String search;
    private String sortBy;
    private boolean isDesc;
    private String errorMessage;
    private String stackTrace;


    public ProductDao() {
        initializeVariables();
    }

    @Override
    public List<Product> findAll() {
        String searchedItem = "%" + search + "%";
        System.out.println("Search item = "+ search+", search by: "+searchedBy);
        SessionFactory factory = HibernateUtil.getSessionFactory();
        List<Product> result = new ArrayList<>();
        try (Session session = factory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root<Product> rootTable = cq.from(Product.class);
                cq.select(rootTable)
                        .where(
                                cb.like(cb.upper(rootTable.get(String.valueOf(searchedBy))), "%" + search.toUpperCase() + "%"));
           cq.orderBy(isDesc ? cb.desc(rootTable.get(sortBy)) : cb.asc(rootTable.get(sortBy)));

            result.addAll(session
                    .createQuery(cq)
//                    .setFirstResult(firstResultOnPage)     // TODO separate with pages
//                    .setMaxResults(NUMBER_OF_RESULTS_PER_PAGE)
                    .list());
            errorMessage = null;
        } catch (HibernateException e) {
            errorMessage = "Something went wrong in communication with Data Base";
            stackTrace = e.getMessage();
        }
        return result;
    }

    @Override
    public void add(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            errorMessage = "Something went wrong in communication with Data Base";
            stackTrace = e.getMessage();
        }
    }

    public void update(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            errorMessage = "Something went wrong in communication with Data Base";
            stackTrace = e.getMessage();
        }
    }

    public void setSearchedBy(String searchedBy) {
        this.searchedBy = searchedBy;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setDesc(boolean desc) {
        isDesc = desc;
    }

    private void initializeVariables() {
        searchedBy = DEFAULT_SEARCH_METHOD_BY;
        search = EMPTY_STRING_INITIALIZER;
        sortBy = SORTING_DEFAULT_METHOD_BY;
        isDesc = false;
    }
}


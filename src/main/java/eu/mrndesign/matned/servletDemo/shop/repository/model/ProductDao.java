package eu.mrndesign.matned.servletDemo.shop.repository.model;

import eu.mrndesign.matned.servletDemo.shop.repository.hibernate.HibernateUtil;
import eu.mrndesign.matned.servletDemo.shop.repository.model.enumRepo.Category;
import eu.mrndesign.matned.servletDemo.shop.repository.model.entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDao implements DaoInterface<Product> {

    private static final String DEFAULT_SEARCH_METHOD_BY = "name";
    private static final String EMPTY_STRING_INITIALIZER = "";
    private static final String SORTING_DEFAULT_METHOD_BY = "id";
    public static final int DEFAULT_MAX_RESULTS = 20;
    public static final int DEFAULT_FIRST_RESULT = 0;

    private String searchedBy;
    private String search;
    private String sortBy;
    private List<Category> categories;
    private int minPrice;
    private int maxPrice;
    private int minQuantity;
    private int maxQuantity;
    private boolean isDesc;
    private int firstResult;
    private int maxResults;

    public ProductDao() {
        initializeVariables();
    }

    @Override
    public List<Product> findAll() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        List<Product> result = new ArrayList<>();
        try (Session session = factory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root<Product> rootTable = cq.from(Product.class);
            In<Category> isIn = cb.in(rootTable.get("category"));
            for (Category c : categories) {
                isIn.value(c);
            }
            cq.select(rootTable)
                    .where(cb.and(
                                cb.like(cb.upper(rootTable.get(String.valueOf(searchedBy))), "%" + search.toUpperCase() + "%"),
                                isIn,
                                cb.between(rootTable.get("price"),minPrice,maxPrice),
                                cb.between(rootTable.get("quantity"),minQuantity,maxQuantity)))
                    .orderBy(isDesc ? cb.desc(rootTable.get(sortBy)) : cb.asc(rootTable.get(sortBy)));
            result.addAll(session
                    .createQuery(cq)
                    .setFirstResult(firstResult)
                    .setMaxResults(maxResults)
                    .list());
        } catch (HibernateException e) {
            error("find all failed");
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
            error("Add failed");
        }
    }

    @Override
    public void update(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            error("Update failed");
        }
    }

    @Override
    public void remove(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.remove(product);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            error("Remove failed");
        }
    }

    @Override
    public void sort(String sortBy) {
        if(this.sortBy.equals(sortBy)) isDesc = !isDesc;
        else isDesc = false;
        this.sortBy = sortBy;
    }

    @Override
    public int getNumberOfRecords() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        int result = 0;
        try (Session session = factory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Product> rootTable = cq.from(Product.class);
            cq.select(cb.count(rootTable));
            result = Math.toIntExact(session.createQuery(cq).getSingleResult());
        }catch (HibernateException e){
            error("Couldn't get records count");
        }
        return result;
    }

    @Override
    public int getMax(String column) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        int result = 0;
        try (Session session = factory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
            Root<Product> rootTable = cq.from(Product.class);
            cq.select(cb.max(rootTable.get(column)));
            result = session.createQuery(cq).getSingleResult();
        }catch (HibernateException e){
            error("Couldn't get the max "+column);
        }
        return result;
    }

    @Override
    public int getAbsoluteQuantity(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        int result = 0;
        try (Session session = factory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root<Product> rootTable = cq.from(Product.class);
            cq.select(rootTable)
                    .where(cb.equal(rootTable.get("id"), product.getId()));
            result = session.createQuery(cq).getSingleResult().getQuantity();
        }catch (HibernateException e){
            error("Couldn't get the product quantity");
        }
        return result;
    }

    public void setPage(int page, int maxResults){
        this.firstResult = page * maxResults;
        this.maxResults = maxResults;
    }

    public void setSearchedBy(String searchedBy) {
        this.searchedBy = searchedBy;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setCategories(String category) {
        try {
            categories.clear();
            categories.add(Category.valueOf(category));
        }catch (IllegalArgumentException e){
            categories = Arrays.asList(Category.values());
        }
    }

    public void setNumberCriteria(int minPrice, int maxPrice, int minQuantity, int maxQuantity) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
    }

    private void error(String message) {
        System.out.println("Error message: "+message);
    }

    private void initializeVariables() {
        searchedBy = DEFAULT_SEARCH_METHOD_BY;
        search = EMPTY_STRING_INITIALIZER;
        sortBy = SORTING_DEFAULT_METHOD_BY;
        firstResult = DEFAULT_FIRST_RESULT;
        maxResults = DEFAULT_MAX_RESULTS;
        minPrice = 0;
        maxPrice = getMax("price");
        minQuantity = 0;
        maxQuantity = getMax("quantity");
        categories = Arrays.asList(Category.values());
        isDesc = false;
    }
}


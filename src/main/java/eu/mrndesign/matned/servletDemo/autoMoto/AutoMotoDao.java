package eu.mrndesign.matned.servletDemo.autoMoto;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;


public class AutoMotoDao implements DaoInterface<AutoMoto>{



    @Override
    public List<AutoMoto> find() {
        List result = new LinkedList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<AutoMoto> criteriaQuery = cb.createQuery(AutoMoto.class);
            Root<AutoMoto> rootTable = criteriaQuery.from(AutoMoto.class);
            criteriaQuery.select(rootTable);
            result.addAll(session.createQuery(criteriaQuery).list());
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void add(AutoMoto autoMoto) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.save(autoMoto);
        session.getTransaction().commit();
        sessionFactory.close();
    }

}

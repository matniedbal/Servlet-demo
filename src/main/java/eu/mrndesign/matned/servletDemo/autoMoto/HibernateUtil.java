package eu.mrndesign.matned.servletDemo.autoMoto;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {

        try {
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}

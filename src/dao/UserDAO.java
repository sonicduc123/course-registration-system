package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.User;
import util.HibernateUtil;
import java.util.List;

public class UserDAO {
    public static List<User> getListUser() {
        List<User> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select s from User s";
            Query query = session.createQuery(hql);
            list = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return list;
    }
}

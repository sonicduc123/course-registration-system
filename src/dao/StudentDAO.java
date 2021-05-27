package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Student;
import util.HibernateUtil;
import java.util.List;

public class StudentDAO {
    public static List<Student> getListStudent() {
        List<Student> list = null;

        try {
            String hql = "select s from Student s";
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery(hql);
            list = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        }
        return list;
    }
}

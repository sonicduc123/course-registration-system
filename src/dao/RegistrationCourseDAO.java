package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.IDRegistrationCourse;
import pojo.RegistrationCourse;
import util.HibernateUtil;

import java.util.List;

public class RegistrationCourseDAO {
    public static List<RegistrationCourse> GetList() {
        List<RegistrationCourse> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select rc from RegistrationCourse rc";
            Query query = session.createQuery(hql);
            list = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return list;
    }

    public static RegistrationCourse GetInfo(IDRegistrationCourse idRegistrationCourse) {
        RegistrationCourse registrationCourse = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            registrationCourse = (RegistrationCourse) session.get(RegistrationCourse.class, idRegistrationCourse);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return registrationCourse;
    }

    public static boolean Add(RegistrationCourse registrationCourse) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (RegistrationCourseDAO.GetInfo(registrationCourse.getId()) != null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(registrationCourse);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        System.out.println("Add registration course success");
        return true;
    }

    public static boolean Delete(IDRegistrationCourse idRegistrationCourse) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RegistrationCourse registrationCourse = GetInfo(idRegistrationCourse);
        if (registrationCourse == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(registrationCourse);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        System.out.println("Delete registration course success");
        return true;
    }
}

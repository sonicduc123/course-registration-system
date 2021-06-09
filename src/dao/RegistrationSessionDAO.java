package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.IDSession;
import pojo.RegistrationSession;
import util.HibernateUtil;

import java.util.List;

public class RegistrationSessionDAO {
    public static List<RegistrationSession> GetList() {
        List<RegistrationSession> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select rs from RegistrationSession rs";
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

    public static RegistrationSession GetInfo(IDSession idSession) {
        RegistrationSession registrationSession = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            registrationSession = (RegistrationSession) session.get(RegistrationSession.class, idSession);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return registrationSession;
    }

    public static boolean Add(RegistrationSession registrationSession) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (RegistrationSessionDAO.GetInfo(registrationSession.getId()) != null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(registrationSession);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        System.out.println("Add session success");
        return true;
    }

    public static boolean Delete(IDSession idSession) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RegistrationSession registrationSession = GetInfo(idSession);
        if (registrationSession == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(registrationSession);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        System.out.println("Add session success");
        return true;
    }
}

package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Classroom;
import util.HibernateUtil;

import java.util.List;

public class ClassroomDAO {
    public static List<Classroom> GetList() {
        List<Classroom> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select c from Classroom c";
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

    public static Classroom GetInfo(int id) {
        Classroom classroom = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            classroom = (Classroom) session.get(Classroom.class, id);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return classroom;
    }

    public static boolean Add(Classroom classroom) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (ClassroomDAO.GetInfo(classroom.getId()) != null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(classroom);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        System.out.println("Add classroom success");
        return true;
    }

    public static boolean Delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Classroom classroom = GetInfo(id);
        if (classroom == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(classroom);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        System.out.println("Delete classroom success");
        return true;
    }
}

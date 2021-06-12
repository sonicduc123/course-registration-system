package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Teacher;
import util.HibernateUtil;

import java.util.List;

public class TeacherDAO {
    public static List<Teacher> GetList() {
        List<Teacher> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select t from Teacher t";
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

    public static Teacher GetInfo(int id) {
        Teacher teacher = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            teacher = (Teacher) session.get(Teacher.class, id);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return teacher;
    }

    public static boolean Add(Teacher teacher) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (TeacherDAO.GetInfo(teacher.getId()) != null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(teacher);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        System.out.println("Add teacher success");
        return true;
    }

    public static boolean Delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Teacher teacher = GetInfo(id);
        if (teacher == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(teacher);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        System.out.println("Delete teacher success");
        return true;
    }
}

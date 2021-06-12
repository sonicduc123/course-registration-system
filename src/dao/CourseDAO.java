package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Course;
import pojo.IDSession;
import pojo.RegistrationSession;
import util.HibernateUtil;

import java.util.List;

public class CourseDAO {
    public static List<Course> GetList() {
        List<Course> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select c from Course c";
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

    public static List<Course> GetListByRegistrationSession(RegistrationSession registrationSession) {
        List<Course> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select c from Course c where c.registrationSession=:registrationSession";
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

    public static Course GetInfo(int id) {
        Course course = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            course = (Course) session.get(Course.class, id);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return course;
    }

    public static boolean Add(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (CourseDAO.GetInfo(course.getId()) != null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        System.out.println("Add course success");
        return true;
    }

    public static boolean Update(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (CourseDAO.GetInfo(course.getId()) != null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        System.out.println("Update course success");
        return true;
    }

    public static boolean Delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Course course = GetInfo(id);
        if (course == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(course);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        System.out.println("Delete course success");
        return true;
    }
}

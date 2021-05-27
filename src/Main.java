import dao.StudentDAO;
import pojo.Student;

import java.util.List;

public class Main {
    public static void main (String[] args) {
        List<Student> list = StudentDAO.getListStudent();
        for (Student s : list) {
            System.out.println("StudentID: " + s.getStudentID());
            System.out.println("Name: " + s.getName());
        }
    }
}

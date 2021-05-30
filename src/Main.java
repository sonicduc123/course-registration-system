import screen.LoginScreen;

public class Main {
    public static void main (String[] args) {
//        List<Student> list = StudentDAO.getListStudent();
//        for (Student s : list) {
//            System.out.println("StudentID: " + s.getStudentID());
//            System.out.println("Name: " + s.getName());
//            System.out.println("ID: " + s.getId());
//            System.out.println("username: " + s.getUsername());
//            System.out.println("password: " + s.getPassword());
//            System.out.println("email: " + s.getEmail());
//            System.out.println("phone: " + s.getPhone());
//        }
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.Run();
    }
}

import org.hibernate.Session;
import screen.LoginScreen;
import util.HibernateUtil;

public class Main {
    public static void main (String[] args) {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.Run();
    }
}

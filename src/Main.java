import dao.RegistrationSessionDAO;
import dao.UserDAO;
import org.hibernate.Session;
import pojo.User;
import screen.AffairHomeScreen;
import screen.DataUtil;
import screen.LoginScreen;
import util.HibernateUtil;

import java.util.List;

public class Main {
    public static void main (String[] args) {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.Run();
    }
}

import dao.UserDAO;
import org.hibernate.Session;
import pojo.User;
import screen.AffairHomeScreen;
import screen.LoginScreen;
import util.HibernateUtil;

import java.util.List;

public class Main {
    public static void main (String[] args) {
        AffairHomeScreen.list = UserDAO.getListUser();
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.Run();
    }
}

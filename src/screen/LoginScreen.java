package screen;

import dao.RegistrationSessionDAO;
import dao.UserDAO;
import org.jboss.jandex.Main;
import pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginScreen implements ActionListener {
    private JFrame frame;
    private JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
    private JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 33, 20));
    private Container pane;

    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton btnLogin;

    public void addComponentsToPane() {
        pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        pane.add(Box.createVerticalGlue());

        addInput("Username");
        addPassword("Password");
        btnLogin = Function.AddAButton("Login", pane);
        btnLogin.addActionListener(this);

        pane.add(Box.createVerticalGlue());
    }

    private void addInput(String labelValue) {
        usernameLabel = new JLabel(labelValue);
        usernameTextField = new JTextField("", 15);
        usernamePanel.setMaximumSize(new Dimension(400, 0));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);
        pane.add(usernamePanel);
    }

    private void addPassword(String labelValue) {
        passwordLabel = new JLabel(labelValue);
        passwordField = new JPasswordField("", 15);
        passwordField.addActionListener(this);

        passwordPanel.setMaximumSize(new Dimension(400, 0));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        pane.add(passwordPanel);
    }

    private void createAndShowGUI() {
        //create and set up the window
        frame = new JFrame("Course Registration System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set up the content pane
        addComponentsToPane();

        //display the window
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setMinimumSize(new Dimension((int)screenDimension.getWidth()/2, (int)screenDimension.getHeight()/2));
        frame.setVisible(true);
    }

    public void Run() {
        DataUtil.listUser = UserDAO.getListUser();
        javax.swing.SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        boolean isExist = false;
        for(User u: DataUtil.listUser) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                isExist = true;
                AffairHomeScreen homeScreen = new AffairHomeScreen();
                homeScreen.Run();
                frame.dispose();
            }
        }
        if (!isExist) {
            JOptionPane.showMessageDialog(frame, "Login false", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

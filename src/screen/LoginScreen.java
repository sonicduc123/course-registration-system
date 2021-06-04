package screen;

import dao.UserDAO;
import pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginScreen implements ActionListener {
    private List<User> list;

    private JFrame frame;
    private JPanel usernamePanel = new JPanel();
    private JPanel passwordPanel = new JPanel();
    private JPanel loginPanel = new JPanel();
    private Container pane;

    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;

    public void addComponentsToPane() {
        pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        addInput("Username");
        addPassword("Password");
        addAButton("Login");
    }

    private void addAButton(String text) {
        loginButton = new JButton(text);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(loginButton);
        pane.add(loginPanel);
        loginButton.addActionListener(this);
    }

    private void addInput(String labelValue) {
        usernameLabel = new JLabel(labelValue);
        usernameTextField = new JTextField("", 15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);
        pane.add(usernamePanel);
    }

    private void addPassword(String labelValue) {
        passwordLabel = new JLabel(labelValue);
        passwordField = new JPasswordField("", 15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        pane.add(passwordPanel);
    }

    private void createAndShowGUI() {
        //create and set up the window
        frame = new JFrame("Course Registration System");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //set up the content pane
        addComponentsToPane();

        //display the window
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setMinimumSize(new Dimension((int)screenDimension.getWidth()/2, (int)screenDimension.getHeight()/2));
        frame.setVisible(true);
    }

    public void Run() {
        list = UserDAO.getListUser();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        boolean isExist = false;
        for(User u: list) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                isExist = true;
                HomeScreen homeScreen = new HomeScreen();
                homeScreen.Run();
                frame.dispose();
            }
        }
        if (!isExist) {
            JOptionPane.showMessageDialog(frame, "Login false", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

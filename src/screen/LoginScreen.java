package screen;

import javax.swing.*;
import java.awt.*;

public class LoginScreen {
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set up the content pane
        addComponentsToPane();

        //display the window
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void Run() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

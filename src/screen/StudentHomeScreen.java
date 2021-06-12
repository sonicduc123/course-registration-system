package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentHomeScreen {
    private JFrame frame;
    private JPanel panelRight = new JPanel();
    private JPanel cards = new JPanel(new CardLayout());
    private  JPanel panelLeft;

    public void AddComponentToPane () {
        Container pane = frame.getContentPane();

        panelLeft = new JPanel(new GridLayout(7,1));

        StudentRegistrationCourse registrationCourse = new StudentRegistrationCourse();
        JPanel panelRegistrationCourse = registrationCourse.CreateScreen();
        cards.add(panelRegistrationCourse, "panel1");

        StudentRegisteredCourse courseSessionScreen = new StudentRegisteredCourse();
        panelRight = courseSessionScreen.CreateScreen();
        cards.add(panelRight, "panel2");

        JButton btnRegistrationCourse = Function.AddAButton("Registration Course", panelLeft);
        JButton btnListCourseSession = Function.AddAButton("Your Courses", panelLeft);
        Color originColor = btnRegistrationCourse.getBackground();
        btnRegistrationCourse.setBackground(Color.cyan);
        btnRegistrationCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRegistrationCourse.setBackground(Color.cyan);
                btnListCourseSession.setBackground(originColor);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "panel1");
            }
        });
        btnListCourseSession.addActionListener(e -> {
            btnListCourseSession.setBackground(Color.cyan);
            btnRegistrationCourse.setBackground(originColor);
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, "panel2");
        });
        panelLeft.add(new JLabel());
        panelLeft.add(new JLabel());
        panelLeft.add(new JLabel());
        panelLeft.add(new JLabel());

        JButton btnLogout = Function.AddAButton("Logout", panelLeft);
        btnLogout.addActionListener(e -> {
            frame.dispose();
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.Run();
        });

        panelLeft.setPreferredSize(new Dimension(300, 900));
        cards.setPreferredSize(new Dimension(1000, 900));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, cards);
        pane.add(splitPane, BorderLayout.CENTER);
    }

    private void CreateAndShowGUI () {
        //create and set up the window
        frame = new JFrame("Course Registration System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set up the content pane
        AddComponentToPane();

        //display window
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setMinimumSize(new Dimension(1200, 650));
        frame.setVisible(true);
    }

    public void Run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CreateAndShowGUI();
            }
        });
    }
}

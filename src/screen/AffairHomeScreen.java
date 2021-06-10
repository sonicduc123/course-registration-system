package screen;

import pojo.RegistrationSession;
import pojo.User;

import javax.swing.*;
import javax.swing.plaf.SplitPaneUI;
import java.awt.*;
import java.util.List;

public class AffairHomeScreen {
    private JFrame frame;
    private JPanel panelRight = new JPanel();
    private JPanel cards = new JPanel(new CardLayout());
    private  JPanel panelLeft;

    public void AddComponentToPane () {
        Container pane = frame.getContentPane();

        panelLeft = new JPanel(new GridLayout(8,1));

        RegistrationSessionScreen courseSessionScreen = new RegistrationSessionScreen();
        panelRight = courseSessionScreen.CreateScreen();
        cards.add(panelRight, "card");

        JButton btnListAffair = Function.AddAButton("List affairs", panelLeft);
        JButton btnListAffair1 = Function.AddAButton("List subjects", panelLeft);
        JButton btnListAffair2 = Function.AddAButton("List semesters", panelLeft);
        JButton btnListAffair3 = Function.AddAButton("List classes", panelLeft);

        JButton btnListCourseSession = Function.AddAButton("List course registration sessions", panelLeft);
        btnListCourseSession.addActionListener(e -> {
            btnListCourseSession.setBackground(Color.cyan);
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, "card");
        });

        JButton btnListAffair5 = Function.AddAButton("Account", panelLeft);

        panelLeft.add(new JLabel());

        JButton btnLogout = Function.AddAButton("Logout", panelLeft);
        btnLogout.addActionListener(e -> {
            frame.dispose();
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.Run();
        });

        panelLeft.setPreferredSize(new Dimension(250, 900));
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
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CreateAndShowGUI();
            }
        });
    }
}

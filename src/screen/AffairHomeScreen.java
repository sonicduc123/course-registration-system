package screen;

import pojo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AffairHomeScreen {
    public static List<User> list;
    private JFrame frame;
    private JPanel panelRight = new JPanel();
    private JPanel cards = new JPanel(new CardLayout());
    private  JPanel panelLeft;

    public void AddComponentToPane () {
        Container pane = frame.getContentPane();

        panelLeft = new JPanel(new GridLayout(8,1));

        CourseSessionScreen courseSessionScreen = new CourseSessionScreen();
        panelRight = courseSessionScreen.CreateScreen();
        cards.add(panelRight, "card");
        cards.setBorder(BorderFactory.createLineBorder(Color.black));

        JButton btnListAffair = AddAButton("Danh sách Giáo vụ", panelLeft);
        JButton btnListAffair1 = AddAButton("Danh sách Môn học", panelLeft);
        JButton btnListAffair2 = AddAButton("Danh sách Học kỳ", panelLeft);
        JButton btnListAffair3 = AddAButton("Danh sách Lớp học", panelLeft);

        JButton btnListCourseSession = AddAButton("Danh sách Kỳ đăng ký học phần", panelLeft);
        btnListCourseSession.addActionListener(e -> {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, "card");
        });

        JButton btnListAffair5 = AddAButton("Quản lý tài khoản", panelLeft);

        panelLeft.add(new JLabel());

        JButton btnLogout = AddAButton("Đăng xuất", panelLeft);
        btnLogout.addActionListener(e -> {
            frame.dispose();
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.Run();
        });

        panelLeft.setPreferredSize(new Dimension(250, 900));
        pane.add(panelLeft, BorderLayout.LINE_START);

        pane.add(cards, BorderLayout.CENTER);
    }

    public JButton AddAButton(String buttonName, Container pane) {
        JButton button = new JButton(buttonName);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(button);
        return button;
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

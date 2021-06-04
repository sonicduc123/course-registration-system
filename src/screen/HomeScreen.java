package screen;

import javax.swing.*;
import java.awt.*;

public class HomeScreen {
    private JFrame frame;

    public void AddComponentToPane () {
        Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(2, 3));

        JButton btnListAffair = Function.AddAButton("Danh sách Giáo vụ", pane);
        JButton btnListAffair1 = Function.AddAButton("Danh sách Môn học", pane);
        JButton btnListAffair2 = Function.AddAButton("Danh sách Học kỳ", pane);
        JButton btnListAffair3 = Function.AddAButton("Danh sách Lớp học", pane);
        JButton btnListAffair4 = Function.AddAButton("Danh sách Kỳ đăng ký học phần", pane);
        JButton btnListAffair5 = Function.AddAButton("Quản lý tài khoản", pane);
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
        frame.setMinimumSize(new Dimension((int)screenDimension.getWidth()/2, (int)screenDimension.getHeight()/2));
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

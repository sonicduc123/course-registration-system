package screen;

import com.sun.xml.bind.v2.model.core.ID;
import dao.*;
import pojo.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentRegisteredCourse {
    private JPanel container;
    private JTable table;
    private StudentRegisteredModelTable tableModel;
    private JScrollPane scrollPane;
    static List<Course> listCourse;

    static void GetListCourse () {
        DataUtil.listCourse = CourseDAO.GetList();
        listCourse = new ArrayList<>();
        DataUtil.listRegisteredCourse = RegistrationCourseDAO.GetList();
        for (RegistrationCourse r: DataUtil.listRegisteredCourse) {
            if (r.getId().getId_student() == DataUtil.user.getId()) {
                for (Course c: DataUtil.listCourse) {
                    if (r.getId().getId_course() == c.getId()) {
                        listCourse.add(c);
                    }
                }
            }
        }
    }

    public StudentRegisteredCourse() {
        container = new JPanel(new BorderLayout());
        table = new JTable();
        scrollPane = new JScrollPane(table);
        tableModel = new StudentRegisteredModelTable(this.table);
        GetListCourse();
        DataUtil.listTeacher = TeacherDAO.GetList();
        DataUtil.listClassroom = ClassroomDAO.GetList();
    }

    public JPanel CreateScreen () {
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Your courses");
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelHeader.add(label);
        panelHeader.add(Box.createVerticalStrut(30));
        container.add(panelHeader, BorderLayout.PAGE_START);
        // Contain button and table
        JPanel panelBody = new JPanel();
        panelBody.setLayout(new BoxLayout(panelBody, BoxLayout.Y_AXIS));

        // add table to pane
        tableModel.setEditable(false);
        table.setModel(tableModel);
        table.setFillsViewportHeight(true);
        table.getColumn("").setCellRenderer(new PanelCell());
        table.getColumn("").setCellEditor(new PanelCell());
        table.setRowHeight(30);
        panelBody.add(scrollPane);
        container.add(panelBody, BorderLayout.CENTER);
        container.add(Box.createHorizontalStrut(10), BorderLayout.LINE_START);
        container.add(Box.createHorizontalStrut(10), BorderLayout.LINE_END);

        return container;
    }
}

// list course table
class StudentRegisteredModelTable extends AbstractTableModel {
    String[] columnName = {"CourseID", "Title", "Credits", "Teacher", "Classroom", "Day Of Week", "Shift", "Slot", ""};
    private JTable table;
    private boolean isEditable;

    public StudentRegisteredModelTable(JTable table) {
        this.table = table;
    }

    @Override
    public int getRowCount() {
        return StudentRegisteredCourse.listCourse.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnName[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 8)
            return true;
        return isEditable;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> { return DataUtil.listCourse.get(rowIndex).getId(); }
            case 1 -> { return DataUtil.listCourse.get(rowIndex).getTitle(); }
            case 2 -> { return DataUtil.listCourse.get(rowIndex).getCredits(); }
            case 3 -> { return DataUtil.listCourse.get(rowIndex).getTeacher().getName(); }
            case 4 -> { return DataUtil.listCourse.get(rowIndex).getClassroom().getName(); }
            case 5 -> { return DataUtil.listCourse.get(rowIndex).getDayweek(); }
            case 6 -> { return DataUtil.listCourse.get(rowIndex).getShift(); }
            case 7 -> { return DataUtil.listCourse.get(rowIndex).getSlot(); }
            case 8 -> {
                // button Register
                JPanel panel = new JPanel();
                panel.setBackground(Color.white);
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

                JButton buttonRegister = new JButton("UnRegister");
                buttonRegister.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (new Date().after(DataUtil.listCourse.get(rowIndex).getRegistrationSession().getFinish())) {
                            JOptionPane.showMessageDialog(table, "Time to unregistered is over", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        IDRegistrationCourse idRegistrationCourse = DataUtil.listRegisteredCourse.get(rowIndex).getId();
                        if (RegistrationCourseDAO.Delete(idRegistrationCourse)) {
                            JOptionPane.showMessageDialog(table, "UnRegister success");
                            DataUtil.listCourse.get(rowIndex).setSlot(DataUtil.listCourse.get(rowIndex).getSlot() + 1);
                            CourseDAO.Update(DataUtil.listCourse.get(rowIndex));
                            DataUtil.listRegisteredCourse = RegistrationCourseDAO.GetList();
                            table.repaint();
                        }
                        else {
                            JOptionPane.showMessageDialog(table, "Error", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                panel.add(Box.createHorizontalGlue());
                panel.add(buttonRegister);
                panel.add(Box.createHorizontalGlue());
                return panel;
            }
        }
        return null;
    }

//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        super.setValueAt(aValue, rowIndex, columnIndex);
//        switch (columnIndex) {
//            case 0 -> {
//                DataUtil.listRegistrationSession.get(rowIndex).getId().setSession((String)aValue);
//            }
//            case 1 -> {
//                DataUtil.listRegistrationSession.get(rowIndex).getId().setYear((String)aValue);
//            }
//
//        }
//    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }
}

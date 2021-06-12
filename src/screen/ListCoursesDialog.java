package screen;

import dao.ClassroomDAO;
import dao.CourseDAO;
import dao.TeacherDAO;
import pojo.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListCoursesDialog {
    private JDialog dialog;
    private JTable table;
    private ListCoursesModelTable tableModel;
    private JScrollPane scrollPane;
    static RegistrationSession registrationSession;
    static List<Course> listCourse;

    static void GetListCourse (RegistrationSession registrationSession) {
        DataUtil.listCourse = CourseDAO.GetList();
        listCourse = new ArrayList<>();
        for (Course c: DataUtil.listCourse) {
            if (c.getRegistrationSession().equals(registrationSession)) {
                listCourse.add(c);
            }
        }
    }

    public ListCoursesDialog(RegistrationSession registrationSession) {
        ListCoursesDialog.registrationSession = registrationSession;
        dialog = new JDialog();
        table = new JTable();
        scrollPane = new JScrollPane(table);
        tableModel = new ListCoursesModelTable(this.table);
        GetListCourse(registrationSession);
        DataUtil.listTeacher = TeacherDAO.GetList();
        DataUtil.listClassroom = ClassroomDAO.GetList();
    }

    public void CreateScreen () {
        dialog.setTitle("List courses");
        dialog.setLayout(new BorderLayout());
        Container container = dialog.getContentPane();
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("List courses");
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelHeader.add(label);
        panelHeader.add(Box.createVerticalStrut(30));
        container.add(panelHeader, BorderLayout.PAGE_START);
        // Contain button and table
        JPanel panelBody = new JPanel();
        panelBody.setLayout(new BoxLayout(panelBody, BoxLayout.Y_AXIS));
        // Button to create new course registration session
        JButton buttonCreate = new JButton("Add a new course");
        buttonCreate.setAlignmentX(Component.RIGHT_ALIGNMENT);
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateANewCourse();
            }
        });
        panelBody.add(buttonCreate);
        panelBody.add(Box.createVerticalStrut(30));

        // Button to edit table
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setAlignmentX(Component.RIGHT_ALIGNMENT);
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setEditable(true);
            }
        });
        panelBody.add(buttonEdit);
        panelBody.add(Box.createVerticalStrut(30));

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

        // setup dialog
        dialog.setModal(true);
        dialog.setSize(1000, 600);
        dialog.setMinimumSize(new Dimension(1000, 600));
        dialog.setVisible(true);
    }

    // Create a dialog to add a new course
    void CreateANewCourse() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add a new course");
        Container pane = dialog.getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel container = new JPanel(new GridLayout(7, 1, 0, 10));

        JLabel labelTitle = new JLabel("Title: ");
        container.add(labelTitle);
        JTextField title = new JTextField("", 15);
        container.add(title);

        JLabel labelCredits = new JLabel("Credits: ");
        container.add(labelCredits);
        JTextField credits = new JTextField("", 15);
        container.add(credits);

        JLabel labelTeacher = new JLabel("Teacher: ");
        container.add(labelTeacher);
        String[] teacherName = new String[DataUtil.listTeacher.size()];
        for (int i = 0; i < DataUtil.listTeacher.size(); i++) {
            teacherName[i] = DataUtil.listTeacher.get(i).getName();
        }
        JComboBox listTeacherName = new JComboBox(teacherName);
        container.add(listTeacherName);

        JLabel labelClassroom = new JLabel("Classroom: ");
        container.add(labelClassroom);
        String[] classroomName = new String[DataUtil.listClassroom.size()];
        for (int i = 0; i < DataUtil.listClassroom.size(); i++) {
            classroomName[i] = DataUtil.listClassroom.get(i).getName();
        }
        JComboBox listClassroom = new JComboBox(classroomName);
        container.add(listClassroom);

        JLabel labelDayweek = new JLabel("Days Of Week: ");
        container.add(labelDayweek);
        String[] dayweek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        JComboBox listDayweek = new JComboBox(dayweek);
        container.add(listDayweek);

        JLabel labelShift = new JLabel("Shift: ");
        container.add(labelShift);
        String[] shift = {"7:30 – 9:30", "9:30 – 11:30", "13:30 – 15:30", "15:30 – 17:30"};
        JComboBox listShift = new JComboBox(shift);
        container.add(listShift);

        JLabel labelSlot = new JLabel("Slot: ");
        container.add(labelSlot);
        JTextField slot = new JTextField("", 15);
        container.add(slot);

        JPanel panelSave = new JPanel();
        JButton buttonSave = Function.AddAButton("Save", panelSave);
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course course = new Course();
                course.setTitle(title.getText());
                course.setCredits(Integer.parseInt(credits.getText()));
                Teacher teacher = new Teacher();
                for (int i = 0; i < teacherName.length; i++) {
                    if (teacherName[i].equals((String)listTeacherName.getSelectedItem())) {
                        teacher.setId(i);
                        teacher.setName(teacherName[i]);
                    }
                }
                course.setTeacher(teacher);
                Classroom classroom = new Classroom();
                for (int i = 0; i < classroomName.length; i++) {
                    if (classroomName[i].equals((String)listClassroom.getSelectedItem())) {
                        classroom.setId(i);
                        classroom.setName(classroomName[i]);
                    }
                }
                course.setClassroom(classroom);
                course.setDayweek((String)listDayweek.getSelectedItem());
                course.setShift((String)listShift.getSelectedItem());
                course.setSlot(Integer.parseInt(slot.getText()));
                course.setRegistrationSession(registrationSession);
                CourseDAO.Add(course);
                GetListCourse(registrationSession);
                table.repaint();
                dialog.dispose();
            }
        });

        pane.add(Box.createVerticalStrut(20), BorderLayout.PAGE_START);
        pane.add(Box.createHorizontalStrut(30), BorderLayout.LINE_START);
        pane.add(container, BorderLayout.CENTER);
        pane.add(Box.createHorizontalStrut(30), BorderLayout.LINE_END);
        pane.add(panelSave, BorderLayout.PAGE_END);

        dialog.setModal(true);
        dialog.setSize(500, 400);
        dialog.setMinimumSize(new Dimension(500, 400));
        dialog.setVisible(true);
    }
}

// list course table
class ListCoursesModelTable extends AbstractTableModel {
    String[] columnName = {"CourseID", "Title", "Credits", "Teacher", "Classroom", "Day Of Week", "Shift", "Slot", ""};
    private JTable table;
    private boolean isEditable;

    public ListCoursesModelTable(JTable table) {
        this.table = table;
    }

    @Override
    public int getRowCount() {
        return ListCoursesDialog.listCourse.size();
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
//          btn Show
//                JPanel panel = new JPanel();
//                panel.setBackground(Color.white);
//                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
//
//                JButton buttonShowListCourses = new JButton("Show");
//                buttonShowListCourses.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//
//                    }
//                });
//                panel.add(Box.createHorizontalGlue());
//                panel.add(buttonShowListCourses);
//                panel.add(Box.createHorizontalGlue());
//                return panel;
//            }
            case 8 -> {
                // button delete
                JPanel panel = new JPanel();
                panel.setBackground(Color.white);
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

                JButton buttonDelete = new JButton("Delete");
                buttonDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CourseDAO.Delete((int)getValueAt(rowIndex, 0));
                        ListCoursesDialog.GetListCourse(ListCoursesDialog.registrationSession);
                        buttonDelete.setVisible(false);
                        table.repaint();
                    }
                });
                panel.add(Box.createHorizontalGlue());
                panel.add(buttonDelete);
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

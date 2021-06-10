package screen;

import dao.RegistrationSessionDAO;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import pojo.IDSession;
import pojo.RegistrationSession;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ListCoursesDialog {
    private JDialog dialog;
    private JTable table;
    private ListCoursesModelTable tableModel;
    private JScrollPane scrollPane;

    public ListCoursesDialog() {
        dialog = new JDialog();
        table = new JTable();
        scrollPane = new JScrollPane(table);
        tableModel = new ListCoursesModelTable(this.table);
    }

    public void CreateScreen () {
        dialog.setTitle("List courses");
        dialog.setLayout(new BorderLayout());
        Container container = dialog.getContentPane();
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("List courses");
        label.setFont(new Font("Serif", Font.PLAIN, 20));
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

        // setup dialog
        dialog.setSize(1000, 600);
        dialog.setMinimumSize(new Dimension(1000, 600));
        dialog.setVisible(true);
    }

    // Create a dialog to add a new course
    void CreateANewCourse() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add a new course");
        Container container = dialog.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JPanel panelYear = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel labelYear = new JLabel("Year: ");
        panelYear.add(labelYear);
        panelYear.add(Box.createHorizontalStrut(55));
        String[] year = {"2020-2021", "2021-2022"};
        JComboBox listYear = new JComboBox(year);
        panelYear.add(listYear);
        panelYear.setAlignmentY(Component.CENTER_ALIGNMENT);
        panelYear.setMaximumSize(new Dimension(400, 50));

        JPanel panelSession = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel labelSession = new JLabel("Session: ");
        panelSession.add(labelSession);
        panelSession.add(Box.createHorizontalStrut(35));
        String[] session = {"HK1", "HK2", "HK3"};
        JComboBox listSession = new JComboBox(session);
        panelSession.add(listSession);
        panelSession.setMaximumSize(new Dimension(400, 50));

        JPanel panelStart = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel labelStart = new JLabel("Start day: ");
        panelStart.add(labelStart);
        panelStart.add(Box.createHorizontalStrut(30));
        UtilDateModel modelStart = new UtilDateModel();
        modelStart.setSelected(true);
        JDatePanelImpl panelDateStart = new JDatePanelImpl(modelStart);
        JDatePickerImpl datePickerStart = new JDatePickerImpl(panelDateStart, new DateLabelFormatter());
        panelStart.add(datePickerStart);
        panelStart.setMaximumSize(new Dimension(400, 50));

        JPanel panelFinish = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel labelFinish = new JLabel("Finish day: ");
        panelFinish.add(labelFinish);
        panelFinish.add(Box.createHorizontalStrut(23));
        UtilDateModel modelFinish = new UtilDateModel();
        modelFinish.setSelected(true);
        JDatePanelImpl panelDateFinish = new JDatePanelImpl(modelFinish);
        JDatePickerImpl datePickerFinish = new JDatePickerImpl(panelDateFinish, new DateLabelFormatter());
        panelFinish.add(datePickerFinish);
        panelFinish.setMaximumSize(new Dimension(400, 50));

        //Date selectedDate = (Date) datePicker.getModel().getValue();

        container.add(Box.createVerticalStrut(10));
        container.add(panelYear);
        container.add(panelSession);
        container.add(panelStart);
        container.add(panelFinish);
        JButton buttonSave = Function.AddAButton("Save", container);
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationSession registrationSession = new RegistrationSession();
                IDSession idSession = new IDSession();
                idSession.setYear((String) listYear.getSelectedItem());
                idSession.setSession((String) listSession.getSelectedItem());
                registrationSession.setId(idSession);
                registrationSession.setStart((Date) datePickerStart.getModel().getValue());
                registrationSession.setFinish((Date) datePickerFinish.getModel().getValue());
                RegistrationSessionDAO.Add(registrationSession);
                DataUtil.listRegistrationSession = RegistrationSessionDAO.GetList();
                table.repaint();
                dialog.dispose();
            }
        });
        dialog.setModal(true);
        dialog.setSize(500, 300);
        dialog.setMinimumSize(new Dimension(500, 300));
        dialog.setVisible(true);
    }
}

// list course table
class ListCoursesModelTable extends AbstractTableModel {
    String[] columnName = {"Session", "Year", "Start day", "Finish day", "List course", ""};
    private JTable table;
    private boolean isEditable;

    public ListCoursesModelTable(JTable table) {
        this.table = table;
    }

    @Override
    public int getRowCount() {
        return DataUtil.listRegistrationSession.size();
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
        return isEditable;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> { return DataUtil.listRegistrationSession.get(rowIndex).getId().getSession(); }
            case 1 -> { return DataUtil.listRegistrationSession.get(rowIndex).getId().getYear(); }
            case 2 -> { return DataUtil.listRegistrationSession.get(rowIndex).getStart(); }
            case 3 -> { return DataUtil.listRegistrationSession.get(rowIndex).getFinish(); }
            case 4 -> {
                JPanel panel = new JPanel();
                panel.setBackground(Color.white);
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

                JButton buttonShowListCourses = new JButton("Show");
                buttonShowListCourses.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                panel.add(Box.createHorizontalGlue());
                panel.add(buttonShowListCourses);
                panel.add(Box.createHorizontalGlue());
                return panel;
            }
            case 5 -> {
                JPanel panel = new JPanel();
                panel.setBackground(Color.white);
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

                JButton buttonDelete = new JButton("Delete");
                buttonDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String session = (String) getValueAt(rowIndex, 0);
                        String year = (String) getValueAt(rowIndex, 1);
                        IDSession idSession = new IDSession(session, year);
                        RegistrationSessionDAO.Delete(idSession);
                        DataUtil.listRegistrationSession = RegistrationSessionDAO.GetList();
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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
        switch (columnIndex) {
            case 0 -> {
                DataUtil.listRegistrationSession.get(rowIndex).getId().setSession((String)aValue);
            }
            case 1 -> {
                DataUtil.listRegistrationSession.get(rowIndex).getId().setYear((String)aValue);
            }

        }
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }
}

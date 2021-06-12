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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegistrationSessionScreen {
    JPanel panel = new JPanel(new BorderLayout());
    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane(table);
    RegistrationSessionModelTable tableModel = new RegistrationSessionModelTable(table);

    public JPanel CreateScreen() {
        //get data
        DataUtil.listRegistrationSession = RegistrationSessionDAO.GetList();

        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("List course registration session");
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelHeader.add(label);
        panelHeader.add(Box.createVerticalStrut(30));
        panel.add(panelHeader, BorderLayout.PAGE_START);
        // Contain button and table
        JPanel panelBody = new JPanel();
        panelBody.setLayout(new BoxLayout(panelBody, BoxLayout.Y_AXIS));
        // Button to create new course registration session
        JButton buttonCreate = new JButton("Add a new course registration session");
        buttonCreate.setAlignmentX(Component.RIGHT_ALIGNMENT);
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateANewRegistrationSession();
            }
        });
        panelBody.add(buttonCreate);
        panelBody.add(Box.createVerticalStrut(30));

        // add table to pane
        tableModel.setEditable(false);
        table.setModel(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);

        table.getColumn("List courses").setCellRenderer(new PanelCell());
        table.getColumn("List courses").setCellEditor(new PanelCell());
        table.getColumn("").setCellRenderer(new PanelCell());
        table.getColumn("").setCellEditor(new PanelCell());
        panelBody.add(scrollPane);
        panel.add(panelBody, BorderLayout.CENTER);
        panel.add(Box.createHorizontalStrut(10), BorderLayout.LINE_START);
        panel.add(Box.createHorizontalStrut(10), BorderLayout.LINE_END);
        return panel;
    }

    // Create a dialog to add a new registration session
    void CreateANewRegistrationSession() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add a new course registration session");
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

// custom table model
// registration session table
class RegistrationSessionModelTable extends AbstractTableModel {
    String[] columnName = {"Session", "Year", "Start day", "Finish day", "List courses", ""};
    private JTable table;
    private boolean isEditable;

    public RegistrationSessionModelTable(JTable table) {
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
        if (columnIndex == 4 || columnIndex == 5) {
            return true;
        }
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
                        ListCoursesDialog listCoursesDialog = new ListCoursesDialog(DataUtil.listRegistrationSession.get(rowIndex));
                        listCoursesDialog.CreateScreen();
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

// custom date format
class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "dd-MM-yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}

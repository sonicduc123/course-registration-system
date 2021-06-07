package screen;

import dao.UserDAO;
import pojo.User;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class CourseSessionScreen {
    JPanel panel = new JPanel(new BorderLayout());
    JTable table = new JTable(new CourseSessionModelTable());
    JScrollPane scrollPane = new JScrollPane(table);

    public JPanel CreateScreen() {
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Danh sách các kỳ đăng ký học phần");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelHeader.add(label);
        panelHeader.add(Box.createVerticalStrut(30));
        panel.add(panelHeader, BorderLayout.PAGE_START);

        JButton button = new JButton("Tạo kỳ đăng ký học phần mới");
        button.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JPanel panelBody = new JPanel();
        panelBody.setLayout(new BoxLayout(panelBody, BoxLayout.Y_AXIS));
        panelBody.add(button);
        panelBody.add(Box.createVerticalStrut(30));
        table.setFillsViewportHeight(true);
        panelBody.add(scrollPane);

        panel.add(panelBody, BorderLayout.CENTER);
        return panel;
    }
}

class CourseSessionModelTable extends AbstractTableModel {
    @Override
    public int getRowCount() {
        return AffairHomeScreen.list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String[] columnName = {"id", "name", "email", "phone", "mssv"};
        return columnName[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> { return AffairHomeScreen.list.get(rowIndex).getId(); }
            case 1 -> { return AffairHomeScreen.list.get(rowIndex).getName(); }
            case 2 -> { return AffairHomeScreen.list.get(rowIndex).getEmail(); }
            case 3 -> { return AffairHomeScreen.list.get(rowIndex).getPhone(); }
            case 4 -> { return AffairHomeScreen.list.get(rowIndex).getUserID(); }
        }
        return null;
    }
}

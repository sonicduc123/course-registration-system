package screen;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class Function {
    public static JButton AddAButton(String buttonName, Container pane) {
        JButton button = new JButton(buttonName);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(button);
        return button;
    }
}

// display and clickable panel in table cell
class PanelCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JPanel panel = (JPanel) value;
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JPanel panel = (JPanel) value;
        return panel;
    }
}

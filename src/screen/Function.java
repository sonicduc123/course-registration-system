package screen;

import javax.swing.*;
import java.awt.*;

public class Function {
    public static JButton AddAButton(String buttonName, Container pane) {
        JButton button = new JButton(buttonName);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(button);
        return button;
    }
}

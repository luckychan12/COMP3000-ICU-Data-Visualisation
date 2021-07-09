package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartPanel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ChartMenuBar extends JMenuBar {
    public ChartMenuBar(ChartPanel panel) {
        JButton btnToggleAbsoluteRelative = new JButton();
        ActionListener buttonListener = e -> panel.toggleAbsolute();
        btnToggleAbsoluteRelative.addActionListener(buttonListener);
        btnToggleAbsoluteRelative.setText("Toggle Absolute/Relative");
        this.add(btnToggleAbsoluteRelative);

        HeaderStyleMenu headerStyleMenu = new HeaderStyleMenu(panel);
        this.add(headerStyleMenu);


    }
}
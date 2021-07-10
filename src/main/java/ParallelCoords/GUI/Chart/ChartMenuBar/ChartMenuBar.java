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
        //btnToggleAbsoluteRelative.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.add(btnToggleAbsoluteRelative);

        HeaderStyleMenu headerStyleMenu = new HeaderStyleMenu(panel);
        this.add(headerStyleMenu);


    }
}
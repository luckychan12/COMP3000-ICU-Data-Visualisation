package ParallelCoords.GUI.MenuBar;

import ParallelCoords.Listeners.DataMenuListener;
import ParallelCoords.Main;

import javax.swing.*;

public class ChartMenu extends JMenu {
    public ChartMenu(Main mainWindow) {
        super("Chart");
        JMenuItem mi1;
        DataMenuListener listener = new DataMenuListener(mainWindow);
        mi1 = new JMenuItem("Generate Chart");
        mi1.addActionListener(listener::importDataHeaders);
        this.add(mi1);
        JMenuItem mi2;
        mi2 = new JMenuItem("Reload Chart");
        mi2.addActionListener(listener::importDataHeaders);
        this.add(mi2);
    }
}

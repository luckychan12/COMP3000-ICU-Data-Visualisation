package ParallelCoords.GUI.TableMenuBar.Buttons;

import ParallelCoords.GUI.TableMenuBar.Listeners.ChartMenuListener;
import ParallelCoords.Main;

import javax.swing.*;

public class ChartMenu extends JMenu {
    public ChartMenu(Main mainWindow) {
        super("Chart");
        JMenuItem mi1;
        ChartMenuListener listener;
        listener = new ChartMenuListener(mainWindow);
        mi1 = new JMenuItem("Generate Chart");
        mi1.addActionListener(listener::generateChart);
        this.add(mi1);
        JMenuItem mi2;
        mi2 = new JMenuItem("Reload Chart");
        //mi2.addActionListener(listener::importDataHeaders);
        this.add(mi2);
    }
}

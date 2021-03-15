package ParallelCoords.GUI.MenuBar;

import ParallelCoords.Listeners.FileMenuListener;
import ParallelCoords.Main;

import javax.swing.*;

public class SettingsMenu extends JMenu {
    public SettingsMenu(Main mainWindow) {
        super("Settings");

        FileMenuListener listener = new FileMenuListener(mainWindow);

        JMenuItem mi1;
        mi1 = new JMenuItem("Import Settings");
        mi1.addActionListener(listener::exit);
        this.add(mi1);
        JMenuItem mi2;
        mi2 = new JMenuItem("GUI Settings");
        mi2.addActionListener(listener::exit);
        this.add(mi2);
        JMenuItem mi3;
        mi3 = new JMenuItem("Chart Settings");
        mi3.addActionListener(listener::exit);
        this.add(mi3);
    }

}

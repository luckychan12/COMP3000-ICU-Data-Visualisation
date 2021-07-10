package ParallelCoords.GUI.TableMenuBar.Buttons;


import ParallelCoords.GUI.TableMenuBar.Listeners.SettingsMenuListener;
import ParallelCoords.Main;

import javax.swing.*;

public class SettingsMenu extends JMenu {
    public SettingsMenu(Main mainWindow) {
        super("Settings");

        SettingsMenuListener listener = new SettingsMenuListener(mainWindow);

        JMenuItem mi1;
        mi1 = new JMenuItem("Set Delimiter");
        mi1.addActionListener(listener::setDelimiter);
        this.add(mi1);
        /*
        JMenuItem mi2;
        mi2 = new JMenuItem("ParallelCoordinates.GUI Settings");
        mi2.addActionListener(listener::exit);
        this.add(mi2);
        JMenuItem mi3;
        mi3 = new JMenuItem("Chart Settings");
        mi3.addActionListener(listener::exit);
        this.add(mi3);
        //*/
    }

}

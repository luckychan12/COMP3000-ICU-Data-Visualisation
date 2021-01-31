package org.ParallelCoords.GUI.MenuBar;

import org.ParallelCoords.Listeners.DataMenuListener;
import org.ParallelCoords.Main;

import javax.swing.*;

public class DataMenu extends JMenu {
    public DataMenu(Main mainWindow){
        super("Data");
        JMenuItem mi;
        DataMenuListener listener = new DataMenuListener(mainWindow);
        mi = new JMenuItem("Import Data");
        mi.addActionListener(listener::importData);
        this.add(mi);
    }
}

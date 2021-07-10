package ParallelCoords.GUI.TableMenuBar.Buttons;

import ParallelCoords.GUI.TableMenuBar.Listeners.DataMenuListener;
import ParallelCoords.Main;

import javax.swing.*;

public class DataImportMenu extends JMenu {
    public DataImportMenu(Main mainWindow){
        super("Data");
        JMenuItem mi1;
        JMenuItem mi2;
        DataMenuListener listener = new DataMenuListener(mainWindow);
        mi1 = new JMenuItem("Import Data with Headers");
        mi1.addActionListener(listener::importDataHeaders);
        mi2 = new JMenuItem("Import Data without Headers");
        mi2.addActionListener(listener::importDataNoHeaders);
        this.add(mi1);
        this.add(mi2);
    }
}

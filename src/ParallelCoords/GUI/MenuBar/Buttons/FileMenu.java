package ParallelCoords.GUI.MenuBar.Buttons;

import ParallelCoords.GUI.MenuBar.Listeners.FileMenuListener;
import ParallelCoords.Main;

import javax.swing.*;
//import java.awt.event.KeyEvent;

public class FileMenu extends JMenu {
    public FileMenu(Main mainWindow) {
        super("File");
        JMenuItem mi;
        FileMenuListener listener = new FileMenuListener(mainWindow);
        mi = new JMenuItem("Exit");
        mi.addActionListener(listener::exit);
        this.add(mi);

    }

}

package org.ParallelCoords.GUI.MenuBar;

import org.ParallelCoords.Listeners.FileMenuListener;
import org.ParallelCoords.Main;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class FileMenu extends JMenu {
    public FileMenu(Main mainWindow) {
        super("File");
        JMenuItem mi;
        FileMenuListener cmd = new FileMenuListener(mainWindow);
        mi = new JMenuItem("Exit");
        mi.addActionListener(cmd::exit);
        this.add(mi);

    }

}

package org.ParallelCoords.GUI.MenuBar;

import org.ParallelCoords.Main;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
    /**
     *  
     */
    private static final long serialVersionUID = 8826487342594709581L;

    private final FileMenu fileMenu;

    public MenuBar(Main mainWindow) {
        this.fileMenu = new FileMenu(mainWindow);
        this.add(fileMenu);

    }
}
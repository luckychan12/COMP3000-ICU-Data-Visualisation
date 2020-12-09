package org.ParallelCoords.Listeners;

import org.ParallelCoords.Main;

import java.awt.event.ActionEvent;

public class FileMenuListener {
    private Main mainWindow;
    public FileMenuListener(Main mainWindow) {
        this.mainWindow = mainWindow;
    }



    public void exit(ActionEvent e) {
        mainWindow.setVisible(false);
        mainWindow.dispose();
        System.exit(0);
    }
}

package ParallelCoords.GUI.MenuBar.Buttons;

import ParallelCoords.Main;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public MenuBar(Main mainWindow) {
        FileMenu fileMenu = new FileMenu(mainWindow);
        this.add(fileMenu);
        DataImportMenu dataImportMenu = new DataImportMenu(mainWindow);
        this.add(dataImportMenu);
        ChartMenu chartMenu = new ChartMenu(mainWindow);
        this.add(chartMenu);
        SettingsMenu settingsMenu = new SettingsMenu(mainWindow);
        this.add(settingsMenu);
    }
}
package ParallelCoords.GUI.TableMenuBar.Buttons;

import ParallelCoords.Main;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {

    Font font;
    SettingsMenu settingsMenu;
    ChartMenu generateChart;
    DataImportMenu dataImportMenu;
    public MenuBar(Main mainWindow) {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        font = new Font("Calibri", Font.BOLD, fontSize );

        settingsMenu = new SettingsMenu(mainWindow);
        settingsMenu.setFont(font);
        this.add(settingsMenu);

        dataImportMenu = new DataImportMenu(mainWindow);
        dataImportMenu.setFont(font);
        this.add(dataImportMenu);

        generateChart = new ChartMenu(mainWindow);
        generateChart.setFont(font);
        this.add(generateChart);

    }

    public void reloadFonts(){
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        font = new Font("Calibri", Font.BOLD, fontSize );
        this.setFont(font);
        settingsMenu.setFont(font);
        generateChart.setFont(font);
        dataImportMenu.setFont(font);

        settingsMenu.reloadFonts();
        dataImportMenu.reloadFonts();
        generateChart.reloadFonts();

    }
}
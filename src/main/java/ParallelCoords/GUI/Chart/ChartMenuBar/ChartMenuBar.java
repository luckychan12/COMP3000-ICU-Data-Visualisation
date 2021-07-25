package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.GUI.TableMenuBar.Buttons.SettingsMenu;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;

public class ChartMenuBar extends JMenuBar {

    SettingsMenu settingsMenu;
    ChartSettingsMenu chartSettingsMenu;
    HeaderStyleMenu headerStyleMenu;
    public ChartMenuBar(ChartPanel panel) {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize );

        settingsMenu = new SettingsMenu(panel.getFrame().getMainWindow(), panel);
        settingsMenu.setFont(font);
        this.add(settingsMenu);

        chartSettingsMenu = new ChartSettingsMenu(panel);
        chartSettingsMenu.setFont(font);
        this.add(chartSettingsMenu);

        headerStyleMenu = new HeaderStyleMenu(panel);
        headerStyleMenu.setFont(font);
        this.add(headerStyleMenu);


    }

    public void reloadFonts() {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);
        this.setFont(font);
        settingsMenu.setFont(font);
        chartSettingsMenu.setFont(font);
        headerStyleMenu.setFont(font);

        settingsMenu.reloadFonts();
        chartSettingsMenu.reloadFonts();
        headerStyleMenu.reloadFonts();


    }
}
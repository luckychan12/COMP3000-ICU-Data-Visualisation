package ParallelCoords.GUI.TableMenuBar.Buttons;

import ParallelCoords.Main;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuBar extends JMenuBar {

    private final ArrayList<JComponent> components = new ArrayList<>();

    public MenuBar(Main mainWindow) {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);

        SettingsMenu settingsMenu = new SettingsMenu(mainWindow);
        components.add(settingsMenu);
        settingsMenu.setFont(font);
        this.add(settingsMenu);

        DataImportMenu dataImportMenu = new DataImportMenu(mainWindow);
        components.add(dataImportMenu);
        dataImportMenu.setFont(font);
        this.add(dataImportMenu);

        ChartMenu generateChart = new ChartMenu(mainWindow);
        components.add(generateChart);
        generateChart.setFont(font);
        this.add(generateChart);

    }

    public void reloadFonts(){
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize );
        for (JComponent component: components){
            component.setFont(font);
        }
    }
}
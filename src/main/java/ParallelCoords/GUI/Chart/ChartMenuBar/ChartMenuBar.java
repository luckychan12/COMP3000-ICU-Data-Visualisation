package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.GUI.TableMenuBar.Buttons.SettingsMenu;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChartMenuBar extends JMenuBar {


    private final ArrayList<JComponent> components = new ArrayList<>();
    DataControlMenu dataControlMenu;
    public ChartMenuBar(ChartPanel panel) {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);

        SettingsMenu settingsMenu = new SettingsMenu(panel.getFrame().getMainWindow(), panel);
        components.add(settingsMenu);
        settingsMenu.setFont(font);
        this.add(settingsMenu);

        HeaderStyleMenu headerStyleMenu = new HeaderStyleMenu(panel);
        components.add(headerStyleMenu);
        headerStyleMenu.setFont(font);
        this.add(headerStyleMenu);

        ChartSettingsMenu chartSettingsMenu = new ChartSettingsMenu(panel);
        components.add(chartSettingsMenu);
        chartSettingsMenu.setFont(font);
        this.add(chartSettingsMenu);

        dataControlMenu = new DataControlMenu(panel);
        components.add(dataControlMenu);
        dataControlMenu.setFont(font);
        this.add(dataControlMenu);


    }

    public void reloadFonts() {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize );
        for (JComponent component: components){
            component.setFont(font);
        }

        dataControlMenu.getResetColours().setFont(font);

        for (int i=0; i<dataControlMenu.getItemCount(); ++i) {
            dataControlMenu.getItem(i).setFont(font);
        }

    }

    public DataControlMenu getDataControlMenu() {
        return dataControlMenu;
    }
}
package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DataControlMenu extends JMenu {
    private ArrayList<ColumnDataMenu> menus;


    private ChartPanel panel;
    DataControlMenu(ChartPanel panel){
        super("Data Control Menu");
        this.menus = panel.getDataMenus();
        this.panel = panel;
    }



    public void reloadFonts(){
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);
        for (ColumnDataMenu men: menus) {
            men.setFont(font);
            men.reloadFonts();
        }
    }

    public ArrayList<ColumnDataMenu> getMenus() {
        return menus;
    }

}

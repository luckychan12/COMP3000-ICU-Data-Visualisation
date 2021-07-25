package ParallelCoords.GUI.TableMenuBar.Buttons;

import ParallelCoords.GUI.Chart.ChartColourSettings;
import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.GUI.TableMenuBar.Listeners.SettingsMenuListener;
import ParallelCoords.Main;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;

public class SettingsMenu extends JMenu {
    
    private ChartPanel panel = null;
    public SettingsMenu(Main mainWindow) {
        super("Settings");
        setMenuItems(mainWindow);

    }
    public SettingsMenu(Main mainWindow, ChartPanel panel) {
        super("Settings");
        this.panel = panel;
        setMenuItems(mainWindow);

    }

    public void reloadFonts(){
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize );
        genSettings.setFont(font);
        tableHeader.setFont(font);
        table.setFont(font);
        exit.setFont(font);
        chartSettings.setFont(font);
        chartHeader.setFont(font);
        chartTick.setFont(font);
        cm.setFont(font);
    }

    JMenu genSettings;
    JMenuItem tableHeader;
    JMenuItem table;
    JMenuItem exit;
    JMenu chartSettings;
    JMenuItem chartHeader;
    JMenuItem chartTick;
    JMenuItem cm;

    private void setMenuItems(Main mainWindow) {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize );
        this.setFont(font);
        SettingsMenuListener listener = new SettingsMenuListener(mainWindow);

        genSettings = new JMenu();
        JMenuItem menuFont = new JMenuItem("Set MenuBar font size");
        menuFont.addActionListener(listener::setGeneralFontSize);
        menuFont.setFont(font);

        tableHeader = new JMenuItem("Set table header font size");
        tableHeader.addActionListener(listener::setTableHeaderFontSize);
        tableHeader.setFont(font);

        table = new JMenuItem("Set table font size");
        table.addActionListener(listener::setTableFontSize);
        table.setFont(font);

        genSettings.setText("General font size settings");
        genSettings.setFont(font);
        genSettings.add(menuFont);
        genSettings.add(tableHeader);
        genSettings.add(table);


        chartSettings = new JMenu();
        chartSettings.setText("Chart font size settings");
        chartSettings.setFont(font);

        chartHeader = new JMenuItem("Set Chart Header font size");
        chartHeader.addActionListener(listener::setChartHeadersFontSize);
        chartHeader.setFont(font);

        chartTick = new JMenuItem("Set Chart Tick font size");
        chartTick.addActionListener(listener::setChartTickFontSize);
        chartTick.setFont(font);

        chartSettings.add(chartHeader);
        chartSettings.add(chartTick);


        cm = new JMenuItem("Modify line colour RNG");
        cm.setFont(font);
        cm.addActionListener(getActionListener());


        exit = new JMenuItem("Exit Program");
        exit.setFont(font);
        exit.addActionListener(listener::exit);

        this.add(genSettings);
        this.add(chartSettings);
        this.add(cm);
        this.add(exit);
    }

    private ActionListener getActionListener(){
        if (panel == null) {
            return e -> {
                ChartColourSettings cs = new ChartColourSettings();
            };
        }
        else {
            return e -> {
                ChartColourSettings cs = new ChartColourSettings();
                panel.rePrepData(false, false);
            };
        }
    }

}

package ParallelCoords.GUI.TableMenuBar.Buttons;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.GUI.TableMenuBar.Listeners.SettingsMenuListener;
import ParallelCoords.Main;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
//import java.awt.event.KeyEvent;

public class SettingsMenu extends JMenu {
    ArrayList<JComponent> components = new ArrayList<>();
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

    private void setMenuItems(Main mainWindow) {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize );
        this.setFont(font);
        SettingsMenuListener listener = new SettingsMenuListener(mainWindow);

        JMenuItem menuFont = new JMenuItem("Set MenuBar font size");
        components.add(menuFont);
        menuFont.addActionListener(listener::setGeneralFontSize);

        JMenuItem tableHeader = new JMenuItem("Set table header font size");
        components.add(tableHeader);
        tableHeader.addActionListener(listener::setTableHeaderFontSize);

        JMenuItem table = new JMenuItem("Set table font size");
        components.add(table);
        table.addActionListener(listener::setTableFontSize);

        JMenu genSettings = new JMenu();
        components.add(genSettings);
        genSettings.setText("General font size settings");
        genSettings.add(menuFont);
        genSettings.add(tableHeader);
        genSettings.add(table);


        JMenu chartFontSettings = new JMenu();
        components.add(chartFontSettings);
        chartFontSettings.setText("Chart font size settings");

        JMenuItem chartHeader = new JMenuItem("Set Chart Header font size");
        components.add(chartHeader);
        chartHeader.addActionListener(listener::setChartHeadersFontSize);

        JMenuItem chartTick = new JMenuItem("Set Chart Tick font size");
        components.add(chartTick);
        chartTick.addActionListener(listener::setChartTickFontSize);


        chartFontSettings.add(chartHeader);
        chartFontSettings.add(chartTick);

        JMenuItem chartFilterColour = new JMenuItem("Change filter slider colour");
        components.add(chartFilterColour);
        chartFilterColour.addActionListener(listener.getChartFilterListener(panel));

        JMenuItem chartLineColours = new JMenuItem("Modify line colour RNG");
        components.add(chartLineColours);
        chartLineColours.addActionListener(listener.getChartLineListener(panel));

        JMenu chartColours = new JMenu();
        components.add(chartColours);
        chartColours.setText("Change chart colour settings");
        chartColours.add(chartLineColours);
        chartColours.add(chartFilterColour);

        JMenu chartScaleSettings = new JMenu();
        components.add(chartScaleSettings);
        chartScaleSettings.setText("Chart scale settings");

        JMenuItem chartZoom = new JMenuItem("Toggle chart zoom");
        components.add(chartZoom);
        chartZoom.addActionListener(listener::toggleChartZoom);


        JMenuItem axesPerScreen = new JMenuItem("Set number of axes to display per screen width");
        components.add(axesPerScreen);
        axesPerScreen.addActionListener(listener::setAxesPerScreenWidth);

        chartScaleSettings.add(chartZoom);
        chartScaleSettings.add(axesPerScreen);

        JMenuItem exit = new JMenuItem("Exit Program");
        components.add(exit);
        exit.addActionListener(listener::exit);

        this.add(genSettings);
        this.add(chartFontSettings);
        this.add(chartScaleSettings);
        this.add(chartColours);
        this.add(exit);
        reloadFonts();
    }



    public void reloadFonts(){
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize );
        for (JComponent component: components){
            component.setFont(font);
        }
    }
}

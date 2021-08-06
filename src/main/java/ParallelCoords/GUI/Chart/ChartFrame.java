package ParallelCoords.GUI.Chart;

import ParallelCoords.Data.DataTable;
import ParallelCoords.GUI.Chart.ChartMenuBar.ChartMenuBar;
import ParallelCoords.GUI.Chart.ChartMenuBar.ColumnDataMenu;
import ParallelCoords.GUI.Chart.ChartMenuBar.DataControlMenu;
import ParallelCoords.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChartFrame extends JFrame {

    private final ChartPanel panel;
    private final Main mainWindow;
    private final ChartMenuBar menu;

    public ChartFrame(DataTable displayTable, Main main) {
        super("Chart");
        this.mainWindow = main;
        var gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Dimension screenSize;
        try {
            screenSize = new Dimension(gEnv.getScreenDevices()[0].getDisplayMode().getWidth(), gEnv.getScreenDevices()[0].getDisplayMode().getHeight());
        } catch (Exception e) {
            try {
                screenSize = getToolkit().getScreenSize();
            } catch (HeadlessException e1) {
                screenSize = new Dimension(1200, 800);
            }
        }
        getContentPane().setLayout(null);

        panel = new ChartPanel(this, screenSize, displayTable);
        panel.setLayout(null);
        menu = new ChartMenuBar(panel);
        this.setJMenuBar(menu);
        initDataControlMenu();
        JScrollPane display = new JScrollPane(panel);
        display.getHorizontalScrollBar().setUnitIncrement(20);
        setContentPane(display);
        setLocation(screenSize.width, screenSize.height);
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void reprepData(boolean showWarning) {
        panel.rePrepData(false, showWarning);
    }

    public ChartPanel getPanel() {
        return panel;
    }

    public ChartMenuBar getMenu() {
        return menu;
    }

    public Main getMainWindow() {
        return mainWindow;
    }
    public void initDataControlMenu(){
        DataControlMenu menu = getMenu().getDataControlMenu();
        menu.removeAll();
        menu.addResetColours();
        ArrayList<ColumnDataMenu> subMenus = panel.getDataMenus();
        int i = 0;
        for (ColumnDataMenu dataMenu: subMenus) {
            menu.add(dataMenu);
        }
        menu.reloadFonts();
    }
}

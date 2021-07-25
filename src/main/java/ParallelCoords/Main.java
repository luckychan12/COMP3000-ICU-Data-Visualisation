package ParallelCoords;

import ParallelCoords.Data.Data;
import ParallelCoords.GUI.DataListView.DataDisplayTable;
import ParallelCoords.GUI.Chart.ChartFrame;
import ParallelCoords.GUI.TableMenuBar.Buttons.MenuBar;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;


public class Main extends JFrame {
    private MenuBar menuBar;
    private DataDisplayTable dataDisplayTable;
    private ArrayList<ChartFrame> chartWindows = new ArrayList<>();
    public Main() {
        super("Ivan Chan (ICU) Data Visualisation");
        loadSettings();
        this.menuBar = new MenuBar(this);
        this.setJMenuBar(this.menuBar);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setWindow();
    }

    public void addChart(ChartFrame chartFrame){
        chartWindows.add(chartFrame);
    }

    public void repaintCharts(boolean showWarning){
        for (ChartFrame chart: chartWindows) {
            chart.reprepData(showWarning);
            chart.repaint();
        }
    }

    public void reloadMenuFonts(){
        menuBar.reloadFonts();
        for (ChartFrame chart: chartWindows) {
            chart.getMenu().reloadFonts();
        }
    }

    public DataDisplayTable getDataDisplayTable() {
        return dataDisplayTable;
    }

    private void loadSettings()
        {
            try (InputStream input = new FileInputStream("config.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                UserSettings.getInstance().setProperties(prop);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void initDataPanel(){
            SwingUtilities.invokeLater(() -> {
                if (dataDisplayTable == null) {
                    dataDisplayTable = new DataDisplayTable(Main.this);
                    setContentPane(dataDisplayTable);
                    dataDisplayTable.revalidate();
                } else if(!Data.getInstance().getDataStore().isEmpty()) {
                    dataDisplayTable.setup();
                    dataDisplayTable.revalidate();
                }
                else{
                    setContentPane(new JPanel());
                    repaint();
                }
            });
        }

        public void fireRevalidate()
        {
            dataDisplayTable.revalidate();
        }

        public void setData(){
            this.initDataPanel();
            this.repaint();

        }
    private void setWindow()
    {
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Dimension screenSize;
        screenSize = new Dimension(1000, 500);
        setLocation(screenSize.width/2, screenSize.height/2);
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
}

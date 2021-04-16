package ParallelCoords;

import ParallelCoords.Data.Data;
import ParallelCoords.DataListView.DataDisplayTable;
import ParallelCoords.GUI.Chart.ChartFrame;
import ParallelCoords.GUI.MenuBar.Buttons.MenuBar;
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

    public void repaintCharts(){
        for (ChartFrame chart: chartWindows) {
            chart.repaint();
        }
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
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    if (dataDisplayTable == null) {
                        dataDisplayTable = new DataDisplayTable(ParallelCoords.Main.this);
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
            //this.rebuildAllChartFrames();
        }
    //*/
    private void setWindow()
    {
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
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
        setLocation(screenSize.width, screenSize.height);
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
}

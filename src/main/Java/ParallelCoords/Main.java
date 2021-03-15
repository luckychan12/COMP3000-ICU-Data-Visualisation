package ParallelCoords;


import java.awt.*;
import javax.swing.*;

import ParallelCoords.Data.Data;
import ParallelCoords.GUI.DataView.DataDisplayTable;
import ParallelCoords.GUI.MenuBar.MenuBar;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Main extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 4008647681872120469L;
    private List<Canvas> chartFrames = new LinkedList<>();
    private DataDisplayTable dataDisplayTable;
    private MenuBar menuBar;
    public Main() {
        super("Ivan Chan (ICU) Data Visualisation");
        loadSettings();
        this.menuBar = new MenuBar(this);
        this.setJMenuBar(this.menuBar);
        setWindow();
    }


    private void loadSettings()
    {
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void initDataPanel(){
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
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
            }
        });
    }

    public void setData(){
        this.initDataPanel();
        this.repaint();
        //this.rebuildAllChartFrames();
    }

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

    /**
     * The main method.
     *
     * @param args
     *            the command line arguments (not used)
     */
    public static void main(String[] args) {
        new Main();
    }

}

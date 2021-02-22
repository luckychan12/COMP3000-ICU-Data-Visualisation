package ParallelCoords;


import java.awt.*;
import javax.swing.*;
import ParallelCoords.GUI.MenuBar.MenuBar;

import java.io.*;
import java.util.Properties;

public class Main extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 4008647681872120469L;

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
        setLocation((int) (0.25 * screenSize.width), (int) (0.25 * screenSize.height));
        setSize((int) (0.5 * screenSize.width), (int) (0.5 * screenSize.height));
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

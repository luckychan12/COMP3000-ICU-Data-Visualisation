package org.ParallelCoords;


import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Main extends JFrame {

    public Main() {
        super("Ivan Chan (ICU) Data Visualisation");

        SetWindow();
    }

    private void SetWindow()
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

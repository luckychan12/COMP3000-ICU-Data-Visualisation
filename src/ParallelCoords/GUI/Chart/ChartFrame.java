package ParallelCoords.GUI.Chart;

import ParallelCoords.Data.DataTable;

import javax.swing.*;
import java.awt.*;

public class ChartFrame extends JFrame {
    public ChartFrame(DataTable displayTable){
        super("Chart");
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

        ChartPanel panel = new ChartPanel(this, screenSize, displayTable);
        JScrollPane display = new JScrollPane(panel);
        setContentPane(display);
        setLocation(screenSize.width, screenSize.height);
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }





}

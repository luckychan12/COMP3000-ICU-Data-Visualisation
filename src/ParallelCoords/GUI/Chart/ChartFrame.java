package ParallelCoords.GUI.Chart;

import ParallelCoords.Data.DataTable;
import ParallelCoords.GUI.Chart.ChartMenuBar.ChartMenuBar;

import javax.swing.*;
import java.awt.*;

public class ChartFrame extends JFrame {

    private ChartPanel panel;

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

        panel = new ChartPanel(this, screenSize, displayTable);
        panel.setLayout(null);
        this.setJMenuBar(new ChartMenuBar(panel));
        JScrollPane display = new JScrollPane(panel);
        display.getHorizontalScrollBar().setUnitIncrement(20);
        setContentPane(display);
        setLocation(screenSize.width, screenSize.height);
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void reprepData(){
        panel.reprepData();
    }





}

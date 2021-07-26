package ParallelCoords.GUI.Chart.Filter;

import ParallelCoords.Data.DataColumn;
import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;

public class Axis extends JComponent {
    boolean edge;
    ChartPanel panel;
    int currX;
    DataColumn column;

    public Axis(int currX, Rectangle bounds, boolean edge, ChartPanel panel) {
        this.edge = edge;
        this.setVisible(true);
        this.panel = panel;
        this.currX = currX;

        column = panel.getDataTable().getColumn(currX);
        setBounds(bounds);
        setLocation(0, 0);
        setBackground(Color.BLACK);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(UserSettings.getInstance().getUserGraphSettings().getAxesThickness()));
        g2.setColor(Color.BLACK);
        drawAxis(g2);
    }

    private void drawAxis(Graphics2D g2) {
        int x = panel.getStartPoint().x + currX * panel.getSegmentSize();
        int yStart = panel.getStartPoint().y - 30;
        int yEnd = panel.getStartPoint().y + panel.getAxisLength();
        g2.drawLine(x, yStart, x, edge ? yEnd + panel.getNullPadding() : yEnd);
    }

}

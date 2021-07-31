package ParallelCoords.GUI.Chart.Filter;

import ParallelCoords.Data.DataColumn;
import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;

public class Axis extends JComponent {
    private final boolean edge;
    private final ChartPanel panel;
    private final int currX;

    public Axis(int currX, Rectangle bounds, boolean edge, ChartPanel panel) {
        this.edge = edge;
        this.setVisible(true);
        this.panel = panel;
        this.currX = currX;

        DataColumn column = panel.getDataTableMask().getColumn(currX);
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
        int yStart = panel.getStartPoint().y;
        int yEnd = panel.getStartPoint().y + panel.getAxisLength();
        g2.drawLine(x, yStart, x, edge ? yEnd + panel.getNullPadding() : yEnd);
        int height = g2.getFontMetrics().getHeight() / 4;
        int nullWidth = g2.getFontMetrics().stringWidth("Text/Null value");
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, UserSettings.getInstance()
                .getUserGraphSettings().getChartTickFontSize());
        g2.setFont(font);

        if (currX == 0 || currX == panel.getSegments()-1) {
            g2.drawString("Text/Null value", x - nullWidth / 2, yEnd + panel.getNullPadding() + height + 15);
            g2.drawLine(x - 4, yEnd + panel.getNullPadding() + height - 2, x + 4, yEnd + panel.getNullPadding() + height - 2);
        }
    }

}

package ParallelCoords.GUI.Chart.Filter;

import ParallelCoords.Data.DataColumn;
import ParallelCoords.GUI.Chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Axis extends JComponent {
    FilterSlider filterSlider;
    boolean edge;
    boolean absolute;
    ChartPanel panel;
    int currX;
    DataColumn column;

    double defaultMax = 5;
    double defaultMin = 0;
    int numTicks = 5;

    public Axis(int currX, Rectangle bounds, boolean edge, ChartPanel panel){
        this.edge = edge;
        this.setVisible(true);
        this.panel = panel;
        this.currX = currX;

        column = panel.getDataTable().getColumn(currX);
        setBounds(bounds);
        setLocation(0,0);
        setBackground(Color.BLACK);

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1.5f));
        g2.setColor(Color.BLACK);
        drawAxis(g2);
        //drawTicks(g2);


    }

    private void drawAxis(Graphics2D g2) {
        int x = panel.getStartPoint().x + currX * panel.getSegmentSize();
        int yStart = panel.getStartPoint().y;
        int yEnd = panel.getStartPoint().y + panel.getAxisLength();
        g2.drawLine( x, yStart , x, edge ? yEnd + panel.getNullPadding() : yEnd);
    }

    private void drawTicks(Graphics2D g2){
        double max = defaultMax;
        double min = defaultMin;
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        g2.setFont(font);
        g2.setColor(Color.BLACK);
        if (absolute){
            max = panel.getDataTable().getColumn(currX).getMaxValue();
            min = panel.getDataTable().getColumn(currX).getMinValue();
            if (max == min){
                max++;
                min--;
            }
        }
        FontMetrics metrics = g2.getFontMetrics(font);
        int height = metrics.getHeight() / 4;
        int nullWidth = metrics.stringWidth("Text/Null value");
        int x = panel.getStartPoint().x + panel.getSegmentSize();
        int y = (panel.getStartPoint().y + panel.getAxisLength());
        if (edge){
            g2.drawString("Text/Null value",x * currX - nullWidth/2,  y + height + 15  + panel.getNullPadding());
            g2.drawLine(x -4, (int) (y + height -2 ), x +4, (int) (y + height -2 + panel.getNullPadding()));
        }


        double range = max - min;
        for (int k = 0; k <= numTicks; k++) {
            float percentage = 1f/numTicks * k;
            String text = round(range - ((range / (float) numTicks) * k) + min,2);
            g2.drawString(text,x - 35,  (int) (y * percentage + height));
            g2.drawLine(x -2, (int) (y * percentage), x +2, (int) (y * percentage));
        }

    }

    private String round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value).setScale(places, RoundingMode.HALF_UP);
        return bd.toPlainString();
    }
}

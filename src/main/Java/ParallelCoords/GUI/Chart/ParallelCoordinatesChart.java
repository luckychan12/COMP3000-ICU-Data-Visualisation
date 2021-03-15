package ParallelCoords.GUI.Chart;

import ParallelCoords.Chart.ParallelCoordinatesModel;
import ParallelCoords.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ParallelCoordinatesChart extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {

    private ParallelCoordinatesModel chart;
    private int marginTop = 20;
    private int marginBottom = 80;
    private int marginLeft = 80;
    private int marginRight = 20;


    public ParallelCoordinatesChart(Main mainWindow, ChartFrame chartFrame, ParallelCoordinatesChart chart){




    }

    public void paintComponent(Graphics g) {
        Graphics graphics;
        BufferedImage canvas;

        if(this.chart.isAntiAliasing() || this.chart.isUseAlpha() ){
            canvas = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
            graphics = canvas.getGraphics();
        }
        else{
            canvas = null;
            graphics = g;
        }
    }





    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }






    private void drawPlotFieldBackground(Graphics g) {
        //TODO Settings
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public Dimension getPreferredSize() {
        //int width = marginLeft + marginRight + chart.getWidth();
        //int height = marginTop + marginBottom + chart.getHeight();
        return new Dimension(1600, 800);
    }

    int getMarginLeft() {
        return marginLeft;
    }

    public ParallelCoordinatesModel getChart() {
        return this.chart;
    }
}

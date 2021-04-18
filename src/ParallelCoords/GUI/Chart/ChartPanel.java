package ParallelCoords.GUI.Chart;

import ParallelCoords.Data.DataColumn;
import ParallelCoords.Data.DataTable;

import javax.swing.*;
import java.awt.*;

public class ChartPanel extends JPanel {
    Dimension screenSize;
    ChartFrame frame;
    int height;
    int width;
    float percentageWidth = 0.9f;
    float percentageHeight = 0.85f;
    float percentageAxisLength = 1f;
    float taskbarPadding = 0.75f;
    BasicStroke lineStroke = new BasicStroke(1.5f);
    //BasicStroke thickStroke = new BasicStroke(2f);
    BasicStroke dashedLine = new BasicStroke(2.2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    DataTable dataTable;

    ChartPanel(ChartFrame frame, Dimension screenSize, DataTable dataTable){
        this.frame = frame;
        this.screenSize = screenSize;
        this.setBackground(Color.white);
        this.setPreferredSize(screenSize);
        this.setAutoscrolls(true);
        this.dataTable = dataTable;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.setBackground(Color.white);
        height = (int) (screenSize.height * taskbarPadding);
        width = screenSize.width;

        int segments = dataTable.getNumberOfColumns();
        int tmp = segments;
        if (segments > 10){
            segments = 10;
        }

        int segmentSize = (int) (width * percentageWidth) /segments;
        Point startPoint = new Point();
        startPoint.x = width / 2 - ((segmentSize/2) * (segments - 1) );
        startPoint.y = (int) (height - height * percentageHeight);
        segments = tmp;
        //percentage height is squared to make percentage axis length relative to percentage height and not the screen height
        int axisLength = (int)(percentageHeight * percentageHeight * percentageAxisLength * height);

        Dimension dim = new Dimension((segmentSize * segments + startPoint.x -30), height);
        this.setPreferredSize(dim);

        drawAxis(g2, startPoint, axisLength, segments, segmentSize);
        drawData(g2, startPoint, axisLength, segments, segmentSize);
        drawTicks(g2, startPoint, axisLength, segments, segmentSize);
        drawHeaders(g2, startPoint, segments, segmentSize);
        g2.setStroke(dashedLine);

        setVisible(true);
    }


    private void drawAxis(Graphics2D g2, Point startPoint, int axisLength, int segments, int segmentSize){
        for (int i=0 ; i < segments; i++)
        {
            g2.drawLine( startPoint.x + i * segmentSize, startPoint.y, startPoint.x + i * segmentSize, startPoint.y + axisLength);
        }
    }

    private void drawTicks(Graphics2D g2, Point startPoint, int axisLength, int segments, int segmentSize){
        int max = 5;
        int min = 0;
        int numTicks = 5;

        g2.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 14));
        g2.setColor(Color.BLACK);
        for (int j = 0; j < segments; j++) {
            double range = max - min;
            for (int k = 0; k <= numTicks; k++) {
                float percentage = 1f/numTicks * k;
                String text = Double.toString(  range - ((range / (float) numTicks) * k + min));
                g2.drawString(text,startPoint.x + segmentSize  * j - 25,  (int) (startPoint.y + axisLength * percentage + 5));
                g2.drawLine(startPoint.x + segmentSize  * j -2, (int) (startPoint.y + axisLength * percentage), startPoint.x + segmentSize * j +2, (int) (startPoint.y + axisLength * percentage));
            }
        }
    }

    private void drawHeaders(Graphics2D g2, Point startPoint, int segments, int segmentSize){
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        g2.setFont(font);
        g2.setColor(Color.BLACK);

        FontMetrics metrics = g2.getFontMetrics(font);

        for (int j = 0; j < segments; j++) {
            int width = metrics.stringWidth(dataTable.getColumn(j).getColumnName());

            if (j%2 != 0){
                g2.drawString(dataTable.getColumn(j).getColumnName(),startPoint.x + segmentSize  * j - width/2, (startPoint.y  - 50));
            }
            else
            {
                g2.drawString(dataTable.getColumn(j).getColumnName(),startPoint.x + segmentSize  * j -width/2, (startPoint.y - 30));
            }
        }
    }





    private void drawData(Graphics2D g2, Point startPoint, int axisLength, int segments, int segmentSize){
        g2.setStroke(lineStroke);

        for (int i = 0; i < dataTable.getMaxSize(); i++) {
            for (int j = 0; j < segments - 1; j++) {
                g2.setStroke(lineStroke);
                g2.setColor(dataTable.getColumn(j).findEntity(i).getLineColor());
                if(dataTable.getColumn(j).findEntity(i).isConfirmedValue()){
                    if(dataTable.getColumn(j+1).findEntity(i).isConfirmedValue()){
                        DataColumn firstColumn = dataTable.getColumn(j);
                        DataColumn secondColumn = dataTable.getColumn(j + 1);
                        double firstPercentage = firstColumn.getValuePercentage(i);
                        double secondPercentage = secondColumn.getValuePercentage(i);
                        g2.drawLine(startPoint.x + segmentSize * j, (int) (startPoint.y + axisLength * firstPercentage),
                                startPoint.x + segmentSize * (j + 1), (int) (startPoint.y + axisLength * secondPercentage));
                    }
                    else {
                        g2.setStroke(dashedLine);
                        DataColumn firstColumn = dataTable.getColumn(j);

                        double firstPercentage = firstColumn.getValuePercentage(i);
                        int nextConfirmed = findNextConfirmed(i,j,segments);
                        if (nextConfirmed != -1){
                            double secondPercentage = dataTable.getColumn(nextConfirmed).getValuePercentage(i);
                            g2.drawLine(startPoint.x + segmentSize * j, (int) (startPoint.y + axisLength * firstPercentage),
                                    startPoint.x + segmentSize * nextConfirmed, (int) (startPoint.y + axisLength * secondPercentage));
                        }else
                        {
                            g2.drawLine(startPoint.x + segmentSize * j, (int) (startPoint.y + axisLength * firstPercentage),
                                    startPoint.x + segmentSize* (segments-1) , startPoint.y + axisLength);
                        }

                    }
                }

                else if(!dataTable.getColumn(j).findEntity(i).isConfirmedValue()){
                    if (j == 0 ){
                        g2.setStroke(dashedLine);
                        if (dataTable.getColumn(j+1).findEntity(i).isConfirmedValue()){
                            double secondPercentage = dataTable.getColumn(j+1).getValuePercentage(i);
                            g2.drawLine(startPoint.x, startPoint.y + axisLength,
                                    startPoint.x + segmentSize * (j+1), (int) (startPoint.y + axisLength * secondPercentage));
                        }
                        else{
                            int nextConfirmed = findNextConfirmed(i,j,segments);
                            if (nextConfirmed == -1) {
                                g2.setStroke(new BasicStroke(
                                        2.5f,
                                        BasicStroke.CAP_ROUND,
                                        BasicStroke.JOIN_ROUND,
                                        1f,
                                        new float[] {1f,10f},
                                        0f));
                                g2.drawLine(startPoint.x, startPoint.y + axisLength,
                                        startPoint.x + segmentSize * (segments -1), startPoint.y + axisLength);
                            }
                            else {
                                double secondPercentage = dataTable.getColumn(nextConfirmed).getValuePercentage(i);
                                g2.drawLine(startPoint.x, startPoint.y + axisLength,
                                        startPoint.x + segmentSize * (nextConfirmed), (int) (startPoint.y + axisLength * secondPercentage));

                            }
                        }
                    }
                }
            }
        }
    }

    private int findNextConfirmed(int row, int col, int segments){
        for (int i = col+1; i < segments ; i++) {
            if (dataTable.getColumn(i).findEntity(row).isConfirmedValue()){
                return i;
            }
        }
        return -1;
    }
}

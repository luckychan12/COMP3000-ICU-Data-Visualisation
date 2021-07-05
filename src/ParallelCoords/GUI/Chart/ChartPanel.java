package ParallelCoords.GUI.Chart;

import ParallelCoords.Data.DataColumn;
import ParallelCoords.Data.DataTable;
import ParallelCoords.GUI.Chart.Filter.Axis;
import ParallelCoords.GUI.Chart.Filter.FilterSlider;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ChartPanel extends JPanel {
    Dimension screenSize;
    ChartFrame frame;
    int height;
    int width;
    int nullPadding = 50;
    float percentageWidth = 0.9f;
    float percentageHeight = 0.85f;
    float percentageAxisLength = 1f;
    float taskbarPadding = 0.75f;

    BasicStroke lineStroke = new BasicStroke(1.5f);
    BasicStroke dotDashStroke = new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL,
            1f, new float[] {10f,20f, 3f, 20f},0f);

    BasicStroke dashedLine = new BasicStroke(2.2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
            0, new float[]{9}, 0);
    DataTable dataTable;
    int segments;
    int segmentSize;
    Point startPoint = new Point();
    int axisLength;
    boolean absolute = false;
    ArrayList<FilterSlider> filterSliders = new ArrayList<>();
    ArrayList<FullLineData> fullLineData = new ArrayList<>();



    ChartPanel(ChartFrame frame, Dimension screenSize, DataTable dataTable){
        this.frame = frame;
        this.screenSize = screenSize;
        this.setBackground(Color.white);
        this.setPreferredSize(screenSize);
        this.setAutoscrolls(true);
        this.dataTable = dataTable;

        for (int i = 0; i < dataTable.getMaxSize(); i++) {
            fullLineData.add(new FullLineData());
        }



        height = (int) (screenSize.height * taskbarPadding);
        width = screenSize.width;

        segments = dataTable.getNumberOfColumns();
        int tmp = segments;
        if (segments > 14){
            segments = 14;
        }
        segmentSize = (int) (width * percentageWidth) /segments;
        startPoint.x = width / 2 - ((segmentSize/2) * (segments - 1) );
        startPoint.y = (int) (height - height * percentageHeight);
        segments = tmp;
        //percentage height is squared to make percentage axis length relative to percentage height and not the screen height
        axisLength = (int)(percentageHeight * percentageHeight * percentageAxisLength * height);
        Dimension dim = new Dimension((segmentSize * segments + startPoint.x -30), height);
        this.setPreferredSize(dim);

        for (int i=0 ; i < segments; i++)
        {
            filterSliders.add(new FilterSlider(startPoint.y, startPoint.y + axisLength, startPoint.x + i * segmentSize,this, i));
        }
        reprepData();
    }

    public ArrayList<FullLineData> getFullLineData() {
        return fullLineData;
    }

    public void reprepData(){
        resetData();
        prepData(startPoint, axisLength, segments, segmentSize);
    }

    private void resetData(){
        fullLineData.clear();
        for (int i = 0; i < dataTable.getMaxSize(); i++) {
            fullLineData.add(new FullLineData());
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.setBackground(Color.white);

        drawAxis(g2, startPoint, axisLength, segments, segmentSize);
        drawData(g2);
        drawTicks(g2, startPoint, axisLength, segments, segmentSize);
        drawHeaders(g2, startPoint, segments, segmentSize);
        g2.setStroke(dashedLine);

        setVisible(true);
    }


    private void drawAxis(Graphics2D g2, Point startPoint, int axisLength, int segments, int segmentSize){

        for (int i=0 ; i < segments; i++) {
            if (i == 0 || i == segments - 1) {
                this.add(new Axis(i, this.getBounds(), true, this));
            }
            else{
                this.add(new Axis( i, this.getBounds(), false, this));
            }

        }

    }

    private void drawTicks(Graphics2D g2, Point startPoint, int axisLength, int segments, int segmentSize){
        double max = 5;

        double min = 0;
        int numTicks = 5;

        g2.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 14));
        g2.setColor(Color.BLACK);
        for (int j = 0; j < segments; j++) {
            if (absolute){
                max = dataTable.getColumn(j).getMaxValue();
                min = dataTable.getColumn(j).getMinValue();

                if (max == min){
                    max++;
                    min--;
                }
            }
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            g2.setFont(font);
            g2.setColor(Color.BLACK);

            FontMetrics metrics = g2.getFontMetrics(font);
            int height = metrics.getHeight() / 4;
            int nullWidth = metrics.stringWidth("Text/Null value");
            if (j == 0 || j == segments -1){
                g2.drawString("Text/Null value",startPoint.x + segmentSize  * j - nullWidth/2,  (int) (startPoint.y + axisLength + nullPadding + height + 15));
                g2.drawLine(startPoint.x + segmentSize  * j -4, (int) (startPoint.y + axisLength + nullPadding + height -2 ), startPoint.x + segmentSize * j +4, (int) (startPoint.y + axisLength + nullPadding + height -2));
            }


            double range = max - min;
            for (int k = 0; k <= numTicks; k++) {
                float percentage = 1f/numTicks * k;

                String text = round(range - ((range / (float) numTicks) * k) + min,2);
                g2.drawString(text,startPoint.x + segmentSize  * j - 35,  (int) (startPoint.y + axisLength * percentage + height));
                g2.drawLine(startPoint.x + segmentSize  * j -2, (int) (startPoint.y + axisLength * percentage), startPoint.x + segmentSize * j +2, (int) (startPoint.y + axisLength * percentage));
            }
        }
    }
    private String round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value).setScale(places, RoundingMode.HALF_UP);
        return bd.toPlainString();
    }

    private void drawHeaders(Graphics2D g2, Point startPoint, int segments, int segmentSize){
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        g2.setFont(font);
        g2.setColor(Color.BLACK);

        FontMetrics metrics = g2.getFontMetrics(font);

        for (int j = 0; j < segments; j++) {
            int width = metrics.stringWidth(dataTable.getColumn(j).getColumnName());

            if (j%2 != 0){
                g2.drawString(dataTable.getColumn(j).getColumnName(),startPoint.x + segmentSize  * j - width/2, (startPoint.y  - 55));
            }
            else
            {
                g2.drawString(dataTable.getColumn(j).getColumnName(),startPoint.x + segmentSize  * j -width/2, (startPoint.y - 35));
            }
        }
    }


    private void prepData(Point startPoint, int axisLength, int segments, int segmentSize){
        int x1, y1, x2, y2;
        for (int i = 0; i < dataTable.getMaxSize(); i++) {
            if (!dataTable.getShowRecord().get(i)){
                continue;
            }
            for (int j = 0; j < segments - 1; j++) {
                // Normal value start
                if(dataTable.getColumn(j).findEntity(i).isConfirmedValue()){
                    boolean nextIsConfirmed = dataTable.getColumn(j+1).findEntity(i).isConfirmedValue();

                    // Normal value to normal value
                    if(nextIsConfirmed){
                        DataColumn firstColumn = dataTable.getColumn(j);
                        DataColumn secondColumn = dataTable.getColumn(j + 1);
                        double firstPercentage = firstColumn.getValuePercentage(i, absolute);
                        double secondPercentage = secondColumn.getValuePercentage(i, absolute);

                        x1 = startPoint.x + segmentSize * j;
                        y1 = (int) (startPoint.y + axisLength * firstPercentage);
                        x2 = startPoint.x + segmentSize * (j + 1);
                        y2 = (int) (startPoint.y + axisLength * secondPercentage);
                        Point point1 = new Point(x1,y1);
                        Point point2 = new Point(x2,y2);

                        fullLineData.get(i).addData(new PartialLineData(point1,point2,
                                dataTable.getColumn(j).findEntity(i).getLineColor(), lineStroke, j, j+1,
                                firstPercentage, secondPercentage));
                    }

                    // Normal value to missing value
                    if (!nextIsConfirmed){
                        DataColumn firstColumn = dataTable.getColumn(j);
                        double firstPercentage = firstColumn.getValuePercentage(i, absolute);
                        int nextConfirmedValue = findNextConfirmed(i,j,segments);

                        // Normal value to edge missing value
                        if (nextConfirmedValue == -1){
                            x1 = startPoint.x + segmentSize * j;
                            y1 = (int) (startPoint.y + axisLength * firstPercentage);
                            x2 = startPoint.x + segmentSize* (segments-1);
                            y2 = startPoint.y + axisLength + nullPadding;
                            Point point1 = new Point(x1,y1);
                            Point point2 = new Point(x2,y2);

                            fullLineData.get(i).addData(new PartialLineData(point1,point2,
                                    dataTable.getColumn(j).findEntity(i).getLineColor(), dotDashStroke, j, segments-1,
                                    firstPercentage, null));
                        }

                        //normal value to edge missing value
                        if (nextConfirmedValue != -1){
                            double secondPercentage = dataTable.getColumn(nextConfirmedValue).getValuePercentage(i, absolute);
                            x1 = startPoint.x + segmentSize * j;
                            y1 = (int) (startPoint.y + axisLength * firstPercentage);
                            x2 = startPoint.x + segmentSize * nextConfirmedValue;
                            y2 = (int) (startPoint.y + axisLength * secondPercentage);
                            Point point1 = new Point(x1,y1);
                            Point point2 = new Point(x2,y2);

                            fullLineData.get(i).addData(new PartialLineData(point1,point2,
                                    dataTable.getColumn(j).findEntity(i).getLineColor(), dashedLine,j,nextConfirmedValue,
                                    firstPercentage, secondPercentage));
                        }
                    }


                }

                // Missing value start
                else if(!dataTable.getColumn(j).findEntity(i).isConfirmedValue()){
                    // edge missing value
                    if (j != 0 ) {
                        continue;
                    }

                    // edge to normal
                    if (dataTable.getColumn(j+1).findEntity(i).isConfirmedValue()){
                        double secondPercentage = dataTable.getColumn(j+1).getValuePercentage(i, absolute);
                        x1 = startPoint.x;
                        y1 = startPoint.y + axisLength + nullPadding;
                        x2 = startPoint.x + segmentSize * (j+1);
                        y2 = (int) (startPoint.y + axisLength * secondPercentage);
                        Point point1 = new Point(x1,y1);
                        Point point2 = new Point(x2,y2);

                        fullLineData.get(i).addData(new PartialLineData(point1,point2,
                                dataTable.getColumn(j).findEntity(i).getLineColor(), dotDashStroke, 0, j+1,
                                null, secondPercentage));
                    }
                }
            }
        }
    }

    private void drawData(Graphics2D g2){
        for (FullLineData data: fullLineData) {
            if (data.showData()){
                for (PartialLineData line:data.getData()) {
                    g2.setColor(line.getColour());
                    g2.setStroke(line.getLineStroke());
                    g2.drawLine(line.getPoint1().x, line.getPoint1().y, line.getPoint2().x,line.getPoint2().y);
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

    public int getSegmentSize() {
        return segmentSize;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public int getAxisLength() {
        return axisLength;
    }

    public int getNullPadding() {
        return nullPadding;
    }

    public DataTable getDataTable() {
        return dataTable;
    }
}

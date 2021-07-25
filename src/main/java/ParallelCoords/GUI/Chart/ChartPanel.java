package ParallelCoords.GUI.Chart;

import ParallelCoords.Data.DataTable;
import ParallelCoords.GUI.Chart.Filter.Axis;
import ParallelCoords.GUI.Chart.Filter.FilterSlider;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ChartPanel extends JPanel {
    Dimension screenSize;
    ChartFrame frame;
    boolean absolute = false;
    int height;
    int width;
    int nullPadding = 50;
    float percentageWidth = 0.9f;
    float percentageHeight = 0.85f;
    float percentageAxisLength = 1.0f;
    float taskbarPadding = 0.75f;
    ArrayList<ArrayList<Integer>> filterPositionStore = new ArrayList<>();

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

    ArrayList<FilterSlider> filterSliders = new ArrayList<>();
    DataDisplay dataDisplay;



    ChartPanel(ChartFrame frame, Dimension screenSize, DataTable dataTable){
        this.frame = frame;
        this.screenSize = screenSize;
        this.setBackground(Color.white);
        this.setPreferredSize(screenSize);
        this.setAutoscrolls(true);
        this.dataTable = dataTable;
        dataDisplay = new DataDisplay(screenSize, dataTable, this);
        this.add(dataDisplay);

        for (int i = 0; i < dataTable.getMaxSize(); i++) {
            addFullLineData();
        }

        calculateInitialPositionValues();


        for (int i=0 ; i < segments; i++)
        {
            filterSliders.add(new FilterSlider(startPoint.y, startPoint.y + axisLength, startPoint.x + i * segmentSize,this, i));
            this.setComponentZOrder(filterSliders.get(i).getUpperSlider(), 1);
            this.setComponentZOrder(filterSliders.get(i).getLowerSlider(), 1);
        }
        rePrepData(true, true);
    }

    private void addFullLineData(){
        FullLineData lineData = new FullLineData();
        lineData.setColor(dataDisplay.genColour());
        dataDisplay.addLineData(lineData);
    }

    public ChartFrame getFrame() {
        return frame;
    }

    public void toggleAbsolute(){
        absolute = !absolute;
        this.rePrepData(false, false);
    }

    public void changeHeaderStyle(){
        calculateInitialPositionValues();
        for (FilterSlider slider:filterSliders) {
            slider.removeSlider();
        }

        for (int i=0 ; i < segments; i++)
        {
            filterSliders.add(new FilterSlider(startPoint.y, startPoint.y + axisLength, startPoint.x + i * segmentSize,this, i));
        }
        rePrepData(false, false);
    }


    public void calculateInitialPositionValues() {
        height = (int) (screenSize.height * taskbarPadding);
        width = screenSize.width;
        int increment = 0;
        percentageHeight = 0.83f;
        if (UserSettings.getInstance().getUserGraphSettings().getHeaderDisplayType().equals("Tilted")){
            increment = 1;
            percentageHeight = 1f - (UserSettings.getInstance().getUserGraphSettings().getTiltedTopPadding() * 0.01f);
        }

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
        this.setPreferredSize(new Dimension((segmentSize * (segments + increment)  + startPoint.x -20), height));
    }

    public void resetFilters(){
        for (FilterSlider filter:filterSliders) {
            filter.resetPos();
        }
    }

    public void storeFilters(){
        filterPositionStore.clear();
        for (FilterSlider filter: filterSliders) {
            filterPositionStore.add(filter.getPositions());
        }
    }
    public void loadFilters(){
        int i = 0;
        for (FilterSlider filter: filterSliders) {
            filter.loadPositions(filterPositionStore.get(i));
            i++;
        }
    }


    public DataDisplay getDataDisplay() {
        return dataDisplay;
    }

    public void rePrepData(Boolean resetFilters, boolean showWarning){

        resetData();
        if (resetFilters) {
            resetFilters();
        }
        dataDisplay.prepData(startPoint, axisLength, segments, segmentSize, showWarning);
        updateFilters();

    }

    private void resetData(){
        dataDisplay.clearData();
        for (int i = 0; i < dataTable.getMaxSize(); i++) {
            addFullLineData();
        }
    }

    private void updateFilters(){
        for (FilterSlider filter: filterSliders) {
            filter.updateValues();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.setBackground(Color.white);
        g2.clearRect(0,0, width, height);
        drawAxis();
        dataDisplay.repaint();
        drawTicks(g2, startPoint, axisLength, segments, segmentSize);
        drawHeaders(g2, startPoint, segments, segmentSize);
        g2.setStroke(dashedLine);

        setVisible(true);
    }

    private void drawAxis(){

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
        double max = UserSettings.getInstance().getUserGraphSettings().getChartAxisMax();


        double min = UserSettings.getInstance().getUserGraphSettings().getChartAxisMin();

        int numTicks = UserSettings.getInstance().getUserGraphSettings().getChartNumTicks() + 1;


        g2.setColor(Color.BLACK);
        for (int j = 0; j < segments; j++) {
            if (!absolute){
                max = dataTable.getColumn(j).calculateMaxValue();
                min = dataTable.getColumn(j).calculateMinValue();

                if (max == min){
                    max++;
                    min--;
                }
            }

            Font font = new Font(Font.SANS_SERIF,Font.BOLD, UserSettings.getInstance()
                    .getUserGraphSettings().getChartTickFontSize());

            FontMetrics metrics = generateFontMetrics(g2, font);
            int height = metrics.getHeight() / 4;
            int nullWidth = metrics.stringWidth("Text/Null value");
            if (j == 0 || j == segments -1){
                g2.drawString("Text/Null value",startPoint.x + segmentSize  * j - nullWidth/2, startPoint.y + axisLength + nullPadding + height + 15);
                g2.drawLine(startPoint.x + segmentSize  * j -4, startPoint.y + axisLength + nullPadding + height -2, startPoint.x + segmentSize * j +4, startPoint.y + axisLength + nullPadding + height -2);
            }


            double range = max - min;
            int width = 3;
            int width2;
            BasicStroke tickStroke = new BasicStroke(2f);
            g2.setStroke(tickStroke);
            for (int k = 0; k <= numTicks; k++) {
                width2 = width;
                float percentage = 1f/numTicks * k;

                String text = round(range - ((range / (float) numTicks) * k) + min,2);
                int strWidth = metrics.stringWidth(text);
                g2.drawString(text,startPoint.x + segmentSize  * j - strWidth - 10,  (int) (startPoint.y + axisLength * percentage + height));
                if (k ==0 || k == numTicks){
                    width2 = width *5;
                }
                g2.drawLine(startPoint.x + segmentSize  * j -width, (int) (startPoint.y + axisLength * percentage), startPoint.x + segmentSize * j +width2, (int) (startPoint.y + axisLength * percentage));


            }
            g2.setStroke(lineStroke);
        }
    }

    private FontMetrics generateFontMetrics(Graphics2D g2, Font font) {
        g2.setFont(font);
        g2.setColor(Color.BLACK);

        return g2.getFontMetrics(font);
    }

    private String round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value).setScale(places, RoundingMode.HALF_UP);
        return bd.toPlainString();
    }

    private void drawHeaders(Graphics2D g2, Point startPoint, int segments, int segmentSize){
        String displayType = UserSettings.getInstance().getUserGraphSettings().getHeaderDisplayType();
        Font font = new Font(Font.SANS_SERIF,Font.PLAIN, UserSettings.getInstance()
                .getUserGraphSettings().getChartHeaderFontSize());
        FontMetrics metrics = generateFontMetrics(g2, font);
        AffineTransform affineTransform = new AffineTransform();
        AffineTransform orig = g2.getTransform();
        affineTransform.rotate(Math.toRadians(0), 0, 0);

        if (displayType.equals("Tilted")){
            affineTransform.rotate(Math.toRadians(-UserSettings.getInstance().getUserGraphSettings().getTiltedAngle()), 0, 0);
        }


        Font rotatedFont = metrics.getFont().deriveFont(affineTransform);
        g2.setFont(rotatedFont);
        for (int j = 0; j < segments; j++) {


            int spacing = 50;

            int height;
            int tiltedHeight = 45;
            int height1 = spacing + metrics.getHeight();
            int height2 = spacing + metrics.getHeight() * 3;

            int width = metrics.stringWidth(dataTable.getColumn(j).getColumnName());
            if (displayType.equals("Tilted")){
                height = tiltedHeight;
                g2.drawString(dataTable.getColumn(j).getColumnName(), startPoint.x + segmentSize * j,
                        (startPoint.y  - height));
            }
            else {

                height = height1;
                if (j%2 == 0) {
                    height = height2;
                    g2.setStroke(new BasicStroke(1.5f));
                    g2.setColor(Color.BLACK);
                    g2.drawLine(startPoint.x + segmentSize  * j, startPoint.y, startPoint.x + segmentSize  * j, startPoint.y - metrics.getHeight() * 4);
                }
                if (dataTable.getColumn(j).getColumnName().length() > 28){
                    String[] words = dataTable.getColumn(j).getColumnName().split("\\s+");
                    int len = words.length;
                    int mid = len/2;
                    StringBuilder headerTop = new StringBuilder();
                    StringBuilder headerBottom = new StringBuilder();
                    int i = 0;
                    for (String word:words) {
                        if(i < mid){
                            headerTop.append(word).append(" ");
                        }
                        else {
                            headerBottom.append(word).append(" ");
                        }
                        i++;
                    }
                    width = metrics.stringWidth(headerTop.toString());
                    g2.drawString(headerTop.toString(),startPoint.x + segmentSize  * j - width/2,
                            (startPoint.y  - height));
                    width = metrics.stringWidth(headerBottom.toString());
                    g2.drawString(headerBottom.toString(),startPoint.x + segmentSize  * j - width/2,
                            (startPoint.y - height + metrics.getHeight()));
                }
                else
                {
                    g2.drawString(dataTable.getColumn(j).getColumnName(),startPoint.x + segmentSize  * j - width/2,
                            (startPoint.y  - height));
                }

            }


        }
        g2.setTransform(orig);
    }

    public boolean isAbsolute() {
        return absolute;
    }

    public Dimension getScreenSize() {
        return screenSize;
    }

    public BasicStroke getDotDashStroke() {
        return dotDashStroke;
    }

    public BasicStroke getDashedLine() {
        return dashedLine;
    }

    public BasicStroke getLineStroke() {
        return lineStroke;
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
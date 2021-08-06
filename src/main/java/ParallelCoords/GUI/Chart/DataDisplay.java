package ParallelCoords.GUI.Chart;

import ParallelCoords.Data.DataColumn;
import ParallelCoords.Data.DataTable;
import ParallelCoords.GUI.Chart.Filter.Filter;
import ParallelCoords.Settings.UserGraphSettings;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

public class DataDisplay extends JComponent {
    private final ArrayList<FullLineData> fullLineData = new ArrayList<>();
    private final Random rand = new Random();
    private DataTable dataTable;
    private final ChartPanel panel;

    DataDisplay(DataTable dataTable, ChartPanel panel) {
        setVisible(true);
        this.dataTable = dataTable;
        this.panel = panel;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public void addLineData(FullLineData data) {
        fullLineData.add(data);
    }

    public void setDrawSize(Dimension dimension) {
        setBounds(0, 0, dimension.width, dimension.height);
    }

    public ArrayList<FullLineData> getFullLineData() {
        return fullLineData;
    }

    public void clearData() {
        fullLineData.clear();
    }

    public Color genRandomColour(Boolean sortedColours, double percentage) {
        float[] weights = UserSettings.getInstance().getUserGraphSettings().getChartColourWeights();
        float brightness = 0.5f * 255;
        float rWeight = (((weights[0])/5) * 255 + brightness)/2;
        float gWeight = (((weights[1])/5) * 255 + brightness)/2;
        float bWeight = (((weights[2])/5) * 255 + brightness)/2;

        Color mix = new Color((int)rWeight,(int)gWeight,(int)bWeight);

        if (sortedColours){
            Color start = new Color(0,0,255);
            Color end = new Color(255,90,0);
            double red = start.getRed() * percentage + end.getRed() * (1 - percentage);
            double green = start.getGreen() * percentage + end.getGreen() * (1 - percentage);
            double blue = start.getBlue() * percentage + end.getBlue() * (1 - percentage);
            mix = new Color((int)red, (int)green, (int)blue);
        }
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        // mix the color
        Color color = mix;
        if (!sortedColours) {
            red = (red + mix.getRed()) / 2;
            green = (green + mix.getGreen()) / 2;
            blue = (blue + mix.getBlue()) / 2;
            color = new Color(red, green, blue);
        }


        return color;
    }

    public void prepData(Point firstAxisStartPoint, int axisLength, int segments, int distanceBetweenAxes, boolean showWarning) {
        int x1, y1, x2, y2;
        Point point1, point2;
        Color lineColour;
        boolean absolute = panel.isAbsolute();
        BasicStroke dashedLine = panel.getDashedLine();
        BasicStroke dotDashStroke = panel.getDotDashStroke();
        BasicStroke lineStroke = panel.getLineStroke();
        int nullPadding = panel.getNullPadding();
        UserGraphSettings settings = UserSettings.getInstance().getUserGraphSettings();

        for (int i = 0; i < dataTable.getMaxSize(); i++) {
            for (int j = 0; j < segments - 1; j++) {
                boolean nextIsConfirmed = dataTable.getColumn(j + 1).findEntity(i).isConfirmedValue();
                // Normal value start
                if (dataTable.getColumn(j).findEntity(i).isConfirmedValue()) {
                    // Normal value to normal value
                    if (nextIsConfirmed) {

                        DataColumn firstColumn = dataTable.getColumn(j);
                        DataColumn secondColumn = dataTable.getColumn(j + 1);
                        if (absolute) {
                            if (firstColumn.getColumnData().get(i).getValue() < settings.getChartAxisMin() ||
                                    firstColumn.getColumnData().get(i).getValue() > settings.getChartAxisMax() ||
                                    secondColumn.getColumnData().get(i).getValue() < settings.getChartAxisMin() ||
                                    secondColumn.getColumnData().get(i).getValue() > settings.getChartAxisMax()) {
                                fullLineData.get(i).setShowData(false);
                                continue;
                            }
                        }

                        double firstPercentage = firstColumn.getValuePercentage(i, absolute, panel.getDataMenus().get(j).isInverted());
                        double secondPercentage = secondColumn.getValuePercentage(i, absolute, panel.getDataMenus().get(j+1).isInverted());
                        x1 = firstAxisStartPoint.x + distanceBetweenAxes * j;
                        y1 = (int) (firstAxisStartPoint.y + axisLength * firstPercentage);
                        x2 = firstAxisStartPoint.x + distanceBetweenAxes * (j + 1);
                        y2 = (int) (firstAxisStartPoint.y + axisLength * secondPercentage);
                        point1 = new Point(x1, y1);
                        point2 = new Point(x2, y2);
                        lineColour = dataTable.getColumn(j).findEntity(i).getLineColor();

                        fullLineData.get(i).addData(new PartialLineData(point1, point2,
                                lineColour, lineStroke, j, j + 1,
                                firstPercentage, secondPercentage));
                    }

                    // Normal value to missing value
                    if (!nextIsConfirmed) {
                        DataColumn firstColumn = dataTable.getColumn(j);
                        double firstPercentage = firstColumn.getValuePercentage(i, absolute, panel.getDataMenus().get(j).isInverted());
                        int nextConfirmedValue = findNextConfirmed(i, j, segments);

                        // Normal value to edge missing value
                        if (nextConfirmedValue == -1) {
                            x1 = firstAxisStartPoint.x + distanceBetweenAxes * j;
                            y1 = (int) (firstAxisStartPoint.y + axisLength * firstPercentage);
                            x2 = firstAxisStartPoint.x + distanceBetweenAxes * (segments - 1);
                            y2 = firstAxisStartPoint.y + axisLength + nullPadding;
                            point1 = new Point(x1, y1);
                            point2 = new Point(x2, y2);
                            lineColour = dataTable.getColumn(j).findEntity(i).getLineColor();

                            fullLineData.get(i).addData(new PartialLineData(point1, point2,
                                    lineColour, dotDashStroke, j, segments - 1,
                                    firstPercentage, null));
                        }

                        //normal value to non edge missing value
                        if (nextConfirmedValue != -1) {
                            double secondPercentage = dataTable.getColumn(nextConfirmedValue).getValuePercentage(i, absolute, panel.getDataMenus().get(nextConfirmedValue).isInverted());
                            x1 = firstAxisStartPoint.x + distanceBetweenAxes * j;
                            y1 = (int) (firstAxisStartPoint.y + axisLength * firstPercentage);
                            x2 = firstAxisStartPoint.x + distanceBetweenAxes * nextConfirmedValue;
                            y2 = (int) (firstAxisStartPoint.y + axisLength * secondPercentage);
                            point1 = new Point(x1, y1);
                            point2 = new Point(x2, y2);
                            lineColour = dataTable.getColumn(j).findEntity(i).getLineColor();

                            fullLineData.get(i).addData(new PartialLineData(point1, point2,
                                    lineColour, dashedLine, j, nextConfirmedValue,
                                    firstPercentage, secondPercentage));
                        }
                    }
                }

                // Missing value start
                else if (!dataTable.getColumn(j).findEntity(i).isConfirmedValue()) {
                    // edge missing value
                    if (j != 0) {
                        continue;
                    }

                    // edge to normal
                    if (nextIsConfirmed) {
                        double secondPercentage = dataTable.getColumn(j + 1).getValuePercentage(i, absolute, panel.getDataMenus().get(j).isInverted());
                        x1 = firstAxisStartPoint.x;
                        y1 = firstAxisStartPoint.y + axisLength + nullPadding;
                        x2 = firstAxisStartPoint.x + distanceBetweenAxes * (j + 1);
                        y2 = (int) (firstAxisStartPoint.y + axisLength * secondPercentage);
                        point1 = new Point(x1, y1);
                        point2 = new Point(x2, y2);
                        lineColour = dataTable.getColumn(j).findEntity(i).getLineColor();

                        fullLineData.get(i).addData(new PartialLineData(point1, point2,
                                lineColour, dotDashStroke, 0, j + 1,
                                null, secondPercentage));
                    }

                    if (!nextIsConfirmed) {
                        int nextConfirmedValue = findNextConfirmed(i, j, segments);

                        if (nextConfirmedValue == -1) {
                            x1 = firstAxisStartPoint.x;
                            y1 = firstAxisStartPoint.y + axisLength + nullPadding;
                            x2 = firstAxisStartPoint.x + distanceBetweenAxes * (segments - 1);
                            y2 = firstAxisStartPoint.y + axisLength + nullPadding;
                            point1 = new Point(x1, y1);
                            point2 = new Point(x2, y2);
                            lineColour = dataTable.getColumn(j).findEntity(i).getLineColor();

                            fullLineData.get(i).addData(new PartialLineData(point1, point2,
                                    lineColour, dotDashStroke, 0, segments - 1,
                                    null, null));
                        }
                        if (nextConfirmedValue != -1) {
                            double secondPercentage = dataTable.getColumn(nextConfirmedValue).getValuePercentage(i, absolute, panel.getDataMenus().get(j).isInverted());
                            x1 = firstAxisStartPoint.x;
                            y1 = firstAxisStartPoint.y + axisLength + nullPadding;
                            x2 = firstAxisStartPoint.x + distanceBetweenAxes * nextConfirmedValue;
                            y2 = (int) (firstAxisStartPoint.y + axisLength * secondPercentage);
                            point1 = new Point(x1, y1);
                            point2 = new Point(x2, y2);
                            lineColour = dataTable.getColumn(j).findEntity(i).getLineColor();

                            fullLineData.get(i).addData(new PartialLineData(point1, point2,
                                    lineColour, dashedLine, 0, nextConfirmedValue,
                                    null, secondPercentage));
                        }
                    }
                }
            }
        }

        if (showWarning) {

            int count = 0;
            for (FullLineData data : fullLineData) {
                if (!data.getShowData()) {
                    count++;
                }
            }
            this.repaint();
            if (count != 0) {

                Object[] options = {"OK", "Dismiss for this dataset"};
                int result = JOptionPane.showOptionDialog(null, "Some data has been omitted due to being outside of the defined axis ranges \n(" +
                                count + " of " + fullLineData.size() + " records have been omitted)" +
                                "\nAxis ranges are currently set to: [Max: " + settings.getChartAxisMax() + "]  [Min: " + settings.getChartAxisMax() + "]",
                        "Warning! Data has been omitted", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), options, null);


                if (result == 1) {
                    dataTable.setDismissWarning(true);
                }

            }
        }
        //panel.columnColour(0);
    }


    private int findNextConfirmed(int row, int col, int segments) {
        for (int i = col + 1; i < segments; i++) {
            if (dataTable.getColumn(i).findEntity(row).isConfirmedValue()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);



        ArrayList<FullLineData> included = new ArrayList<>();
        ArrayList<FullLineData> excluded = new ArrayList<>();
        for (FullLineData data : fullLineData) {

            if (data.filterData()) {
                included.add(data);
            }
            else {
                if (UserSettings.getInstance().getUserGraphSettings().getChartShowFilterTrails()) {
                    excluded.add(data);
                }
            }
        }

        for (FullLineData fullLine : excluded) {
            for (PartialLineData line: fullLine.getData()){
                g2.setColor(new Color(165, 165, 165, 50));
                g2.setStroke(new BasicStroke(0.5f));
                g2.drawLine(line.getPoint1().x, line.getPoint1().y, line.getPoint2().x, line.getPoint2().y);
            }
        }
        for (FullLineData fullLine : included) {
            for (PartialLineData line: fullLine.getData()) {
                g2.setColor(fullLine.getColor());
                g2.setStroke(line.getLineStroke());
                g2.drawLine(line.getPoint1().x, line.getPoint1().y, line.getPoint2().x, line.getPoint2().y);
            }
        }
        for (int i = 0; i < panel.getFilterSliders().size(); i++) {
            int x = panel.getStartPoint().x + panel.getSegmentSize() * i;
            Filter upper = panel.getFilterSliders().get(i).getUpperSlider();
            Filter lower = panel.getFilterSliders().get(i).getLowerSlider();
            g2.setColor(Color.lightGray);
            g2.setStroke(new BasicStroke(UserSettings.getInstance().getUserGraphSettings().getAxesThickness() + 1));
            if(upper.getRealYPos() > panel.getStartPoint().y + upper.getHeight())
            g2.drawLine(x, upper.getYPos() -1, x, panel.getStartPoint().y );
            if (lower.getRealYPos() < (panel.getStartPoint().y + panel.getAxisLength() -lower.getHeight()))
            g2.drawLine(x, lower.getYPos() + lower.getHeight(), x, panel.getStartPoint().y + panel.getAxisLength());
        }
        drawTicks(g2);
    }


    private void drawTicks(Graphics2D g2) {
        UserGraphSettings graphSettings = UserSettings.getInstance().getUserGraphSettings();
        double max = graphSettings.getChartAxisMax();
        double min = graphSettings.getChartAxisMin();
        int numTicks = graphSettings.getChartNumTicks();
        int segments = panel.getSegments();
        int segmentSize = panel.getSegmentSize();
        Point startPoint = panel.getStartPoint();
        int axisLength = panel.getAxisLength();


        g2.setColor(Color.BLACK);
        for (int j = 0; j < panel.getSegments(); j++) {
            if (!panel.isAbsolute()) {
                max = panel.getDataTableMask().getColumn(j).calculateMaxValue();
                min = panel.getDataTableMask().getColumn(j).calculateMinValue();

                if (max == min) {
                    max++;
                    min--;
                }
            }

            Font font = new Font(Font.SANS_SERIF, Font.BOLD, UserSettings.getInstance()
                    .getUserGraphSettings().getChartTickFontSize());

            FontMetrics metrics = g2.getFontMetrics(font);
            g2.setFont(font);
            int height = metrics.getHeight() / 4;


            double range = max - min;
            int width = 3;
            int width2;
            BasicStroke tickStroke = new BasicStroke(graphSettings.getAxesThickness());
            g2.setStroke(tickStroke);
            for (int k = 0; k <= numTicks; k++) {
                float percentage = 1f / numTicks * k;
                String text = round(range - ((range / (float) numTicks) * k) + min, 2);

                int strWidth = metrics.stringWidth(text);
                int yValue;
                if(panel.getDataMenus().get(j).isInverted()) {
                    yValue = (int) (startPoint.y + axisLength * (1-percentage) + height);
                }
                else{
                    yValue = (int) (startPoint.y + axisLength * percentage + height);

                }
                //if (k ==0 || k == numTicks){
                width2 = width * 3;
                //}
                Filter upper = panel.getFilterSliders().get(j).getUpperSlider();
                Filter lower = panel.getFilterSliders().get(j).getLowerSlider();
                if((upper.getRealYPos() > yValue|| lower.getRealYPos()+10 <yValue))
                    g2.setColor(Color.lightGray);
                g2.drawString(text, startPoint.x + segmentSize * j - strWidth - 10, yValue);

                int yValue2 =(int) (startPoint.y + axisLength * percentage);
                if(!(yValue2 < upper.getRealYPos()  && yValue2 > upper.getRealYPos() - upper.getHeight()) &&
                        !(yValue2 < lower.getRealYPos() + lower.getHeight()-10  && yValue2 > lower.getRealYPos())){
                    g2.drawLine(startPoint.x + segmentSize * j - width,yValue2 , startPoint.x + segmentSize * j + width2, yValue2);
                }
                g2.setColor(Color.black);
            }
        }
    }

    private String round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value).setScale(places, RoundingMode.HALF_UP);
        return bd.toPlainString();
    }
}

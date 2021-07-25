package ParallelCoords.GUI.Chart;

import ParallelCoords.Data.DataColumn;
import ParallelCoords.Data.DataTable;
import ParallelCoords.Settings.UserGraphSettings;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DataDisplay extends JComponent {
    private final ArrayList<FullLineData> fullLineData = new ArrayList<>();
    DataTable dataTable;
    ChartPanel panel;

    DataDisplay(DataTable dataTable, ChartPanel panel){
    setVisible(true);
    this.dataTable = dataTable;
    this.panel = panel;
    }
    public void addLineData(FullLineData data){
        fullLineData.add(data);
    }

    public void setDrawSize(Dimension dimension){
        setBounds(0,0, dimension.width, dimension.height);
    }

    public ArrayList<FullLineData> getFullLineData() {
        return fullLineData;
    }

    public void clearData(){
        fullLineData.clear();
    }

    private final Random rand = new Random();
    public Color genColour(){
        float rWeight = UserSettings.getInstance().getUserGraphSettings().getChartRedWeight() + 1;
        float gWeight = UserSettings.getInstance().getUserGraphSettings().getChartGreenWeight() + 1;
        float bWeight = UserSettings.getInstance().getUserGraphSettings().getChartBlueWeight() + 1;

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(1 * (1-(1/rWeight)) + r* ((1/rWeight)), 1 * (1-(1/gWeight)) + g* ((1/gWeight)), 1 * (1-(1/bWeight)) + b * ((1/bWeight))).darker();
    }

    public void prepData(Point startPoint, int axisLength, int segments, int segmentSize, boolean showWarning){
        int x1, y1, x2, y2;
        boolean absolute = panel.isAbsolute();
        BasicStroke dashedLine = panel.getDashedLine();
        BasicStroke dotDashStroke = panel.getDotDashStroke();
        BasicStroke lineStroke = panel.getLineStroke();
        int nullPadding = panel.getNullPadding();
        UserGraphSettings settings = UserSettings.getInstance().getUserGraphSettings();

        for (int i = 0; i < dataTable.getMaxSize(); i++) {
            if (!dataTable.getShowRecord().get(i)){
                continue;
            }
            for (int j = 0; j < segments - 1; j++) {
                boolean nextIsConfirmed = dataTable.getColumn(j+1).findEntity(i).isConfirmedValue();
                // Normal value start
                if(dataTable.getColumn(j).findEntity(i).isConfirmedValue()){
                    // Normal value to normal value
                    if(nextIsConfirmed){

                        DataColumn firstColumn = dataTable.getColumn(j);
                        DataColumn secondColumn = dataTable.getColumn(j + 1);
                        if(absolute) {
                            if (firstColumn.getColumnData().get(i).getValue() < settings.getChartAxisMin() ||
                                    firstColumn.getColumnData().get(i).getValue() > settings.getChartAxisMax() ||
                                    secondColumn.getColumnData().get(i).getValue() < settings.getChartAxisMin() ||
                                    secondColumn.getColumnData().get(i).getValue() > settings.getChartAxisMax()) {
                                fullLineData.get(i).setShowData(false);
                                continue;
                            }
                        }

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

                        //normal value to non edge missing value
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
                    if (nextIsConfirmed){
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

                    if (!nextIsConfirmed){
                        int nextConfirmedValue = findNextConfirmed(i,j,segments);

                        if (nextConfirmedValue == -1){
                            x1 = startPoint.x;
                            y1 = startPoint.y + axisLength + nullPadding;
                            x2 = startPoint.x + segmentSize* (segments-1);
                            y2 = startPoint.y + axisLength + nullPadding;
                            Point point1 = new Point(x1,y1);
                            Point point2 = new Point(x2,y2);
                            fullLineData.get(i).addData(new PartialLineData(point1,point2,
                                    dataTable.getColumn(j).findEntity(i).getLineColor(), dotDashStroke, 0, segments-1,
                                    null, null));
                        }
                        if (nextConfirmedValue != -1){
                            double secondPercentage = dataTable.getColumn(nextConfirmedValue).getValuePercentage(i, absolute);
                            x1 = startPoint.x;
                            y1 = startPoint.y + axisLength + nullPadding;
                            x2 = startPoint.x + segmentSize * nextConfirmedValue;
                            y2 = (int) (startPoint.y + axisLength * secondPercentage);
                            Point point1 = new Point(x1,y1);
                            Point point2 = new Point(x2,y2);

                            fullLineData.get(i).addData(new PartialLineData(point1,point2,
                                    dataTable.getColumn(j).findEntity(i).getLineColor(), dashedLine,0,nextConfirmedValue,
                                    null, secondPercentage));
                        }


                    }



                }
            }
        }

        if(showWarning) {

            int count = 0;
            for (FullLineData data : fullLineData) {
                if (!data.getShowData()) {
                    count++;
                }
            }
            this.repaint();
            if (count != 0) {

                Object[] options = { "OK", "Dismiss for this dataset"};
                int result = JOptionPane.showOptionDialog(null,"Some data has been omitted due to being outside of the defined axis ranges \n(" +
                                count + " of " + fullLineData.size() + " records have been omitted)" +
                                "\nAxis ranges are currently set to: [Max: " + settings.getChartAxisMax() + "]  [Min: " + settings.getChartAxisMax() + "]" ,
                        "Warning! Data has been omitted", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.warningIcon"), options, null);


                if (result == 1) {
                    dataTable.setDismissWarning(true);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (FullLineData data: fullLineData) {
            g2.setColor(data.getColor());
            if (data.filterData()){
                for (PartialLineData line:data.getData()) {
                    g2.setStroke(line.getLineStroke());
                    g2.drawLine(line.getPoint1().x, line.getPoint1().y, line.getPoint2().x,line.getPoint2().y);
                }
            }

        }

    }
}

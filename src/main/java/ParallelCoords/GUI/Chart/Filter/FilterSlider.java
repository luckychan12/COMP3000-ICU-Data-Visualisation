package ParallelCoords.GUI.Chart.Filter;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.GUI.Chart.FullLineData;
import ParallelCoords.GUI.Chart.PartialLineData;
import ParallelCoords.Settings.UserSettings;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class FilterSlider {
    DragBox upperSlider;
    DragBox lowerSlider;

    int upperY;
    int lowerY;
    int upperBound;
    int lowerBound;
    int currX;
    Color upperColour = new Color(157, 78, 0);
    Color lowerColour = new Color(0, 112, 209);
    int thickness = 2;
    ChartPanel panel;
    int segmentNumber;

    public FilterSlider(int upperBound, int lowerBound, int xPos, ChartPanel panel, int segmentNumber) {
        this.panel = panel;
        this.segmentNumber = segmentNumber;
        upperSlider = new DragBox(xPos, upperBound - 8, upperColour, thickness, this, true);
        lowerSlider = new DragBox(xPos, lowerBound + 8, lowerColour, thickness, this, false);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        panel.add(upperSlider);
        panel.add(lowerSlider);
        setUpperBackgroundColour(Color.red);
    }

    public void removeSlider() {
        panel.remove(upperSlider);
        panel.remove(lowerSlider);

    }

    public int getUpperY() {
        return upperSlider.getYPos();
    }

    public void setUpperY(int pos) {
        upperSlider.setYPos(pos);
    }

    public int getLowerY() {
        return lowerSlider.getYPos();
    }

    public void setLowerY(int pos) {
        lowerSlider.setYPos(pos);
    }

    public int getUpperX() {
        return upperSlider.getXPos();
    }

    public void setUpperX(int pos) {
        upperSlider.setXPos(pos);
    }

    public int getLowerX() {
        return lowerSlider.getXPos();
    }

    public void setLowerX(int pos) {
        lowerSlider.setXPos(pos);
    }

    public void setUpperBackgroundColour(Color color) {
        upperSlider.setBackground(color);
    }

    public void setLowerBackgroundColour(Color color) {
        lowerSlider.setBackground(color);
    }

    public void setLowerBorder(Color color, int thickness) {
        upperSlider.setNewBorder(new LineBorder(color, thickness));
        this.thickness = thickness;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
        upperSlider.setOuterBound(upperBound);
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
        lowerSlider.setOuterBound(lowerBound);
    }

    public void remove() {
        panel.remove(upperSlider);
        panel.remove(lowerSlider);
    }

    public DragBox getUpperSlider() {
        return upperSlider;
    }

    public DragBox getLowerSlider() {
        return lowerSlider;
    }

    public boolean checkValue(double value) {
        int upperSliderPos = upperSlider.getRealYPos() - upperBound;
        int lowerSliderPos = lowerSlider.getRealYPos() - upperBound;
        double upperPercentage = 1 - (((float) lowerBound - upperBound) - upperSliderPos) / (lowerBound - upperBound);
        double lowerPercentage = 1 - (((float) lowerBound - upperBound) - lowerSliderPos) / (lowerBound - upperBound);
        return !(value <= upperPercentage) && !(value >= lowerPercentage);
    }

    public void updateValues() {
        for (FullLineData data : panel.getDataDisplay().getFullLineData()) {
            for (PartialLineData line : data.getData()) {

                if (UserSettings.getInstance().getUserGraphSettings().getChartFilterTextData()) {
                    if (line.getGetSegmentEnd() - line.getSegmentStart() != 1) {
                        if (segmentNumber > line.getSegmentStart() && segmentNumber < line.getGetSegmentEnd()
                                && !Objects.isNull(line.getPercentage1()) && !Objects.isNull(line.getPercentage2())) {
                            double deltaX = line.getPoint2().x - line.getPoint1().x;
                            double deltaY = line.getPoint2().y - line.getPoint1().y;
                            double m = deltaY / deltaX;
                            double c = line.getPoint1().y - (m * line.getPoint1().x);
                            double subSegments = (line.getGetSegmentEnd() - line.getSegmentStart());

                            int pos = segmentNumber - line.getSegmentStart();

                            double scaleCoefficient = deltaX / subSegments;
                            double calculatedY = (m * (pos * scaleCoefficient + line.getPoint1().x) + c);

                            double yPercentage = 1 - (((float) lowerBound - upperBound) - (calculatedY - upperBound)) / (lowerBound - upperBound);
                            data.setShowData(segmentNumber, checkValue(yPercentage));
                        }
                    }
                }

                if (line.getSegmentStart() == segmentNumber) {
                    if (!Objects.isNull(line.getPercentage1())) {
                        data.setShowData(segmentNumber, checkValue(line.getPercentage1()));
                    }
                }
                if (line.getGetSegmentEnd() == segmentNumber) {
                    if (!Objects.isNull(line.getPercentage2())) {
                        data.setShowData(segmentNumber, checkValue(line.getPercentage2()));
                    }
                }
            }
        }
        updatePanel();
    }


    public void updatePanel() {
        panel.getDataDisplay().repaint();
    }

    public void resetPos() {
        lowerSlider.resetPos();
        upperSlider.resetPos();
        for (FullLineData data : panel.getDataDisplay().getFullLineData()) {
            data.setShowData(segmentNumber, true);
        }
    }

    public void loadPositions(ArrayList<Integer> list) {
        upperSlider.setYPos(list.get(0));
        lowerSlider.setYPos(list.get(1));
    }

    public ArrayList<Integer> getPositions() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(upperSlider.getYPos());
        list.add(lowerSlider.getYPos());
        return list;
    }


}


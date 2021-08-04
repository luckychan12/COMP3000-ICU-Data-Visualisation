package ParallelCoords.GUI.Chart.Filter;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.GUI.Chart.FullLineData;
import ParallelCoords.GUI.Chart.PartialLineData;
import ParallelCoords.Settings.UserSettings;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class FilterPair {
    private final Filter upperSlider;
    private final Filter lowerSlider;
    private int upperBound;
    private int lowerBound;
    private int thickness = 2;
    private final ChartPanel panel;
    private final int currentSegmentNumber;

    public FilterPair(int upperBound, int lowerBound, int xPos, ChartPanel panel, int segmentNumber) {
        this.panel = panel;
        this.currentSegmentNumber = segmentNumber;
        upperSlider = new Filter(xPos, upperBound, thickness, this, true);
        lowerSlider = new Filter(xPos, lowerBound, thickness, this, false);
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

    public Filter getUpperSlider() {
        return upperSlider;
    }

    public Filter getLowerSlider() {
        return lowerSlider;
    }

    public boolean checkValue(double value) {
        int upperSliderPos = upperSlider.getRealYPos() - upperBound;
        int lowerSliderPos = lowerSlider.getRealYPos() - upperBound;
        double upperPercentage = 1 - (((float) lowerBound - upperBound) - upperSliderPos) / (lowerBound - upperBound);
        double lowerPercentage = 1 - (((float) lowerBound - upperBound) - lowerSliderPos) / (lowerBound - upperBound);
        return !(value < upperPercentage) && !(value > lowerPercentage);
    }

    public void updateValues() {
        for (FullLineData data : panel.getDataDisplay().getFullLineData()) {
            for (PartialLineData line : data.getData()) {

                if (UserSettings.getInstance().getUserGraphSettings().getChartFilterTextData()) {
                    if (line.getGetSegmentEnd() - line.getSegmentStart() != 1) {
                        if (currentSegmentNumber > line.getSegmentStart() && currentSegmentNumber < line.getGetSegmentEnd()
                                && !Objects.isNull(line.getPercentage1()) && !Objects.isNull(line.getPercentage2())) {
                            double deltaX = line.getPoint2().x - line.getPoint1().x;
                            double deltaY = line.getPoint2().y - line.getPoint1().y;
                            double m = deltaY / deltaX;
                            double c = line.getPoint1().y - (m * line.getPoint1().x);
                            double subSegments = (line.getGetSegmentEnd() - line.getSegmentStart());

                            int segmentRelativeToLine = currentSegmentNumber - line.getSegmentStart();

                            double scaleCoefficient = deltaX / subSegments;
                            double calculatedY = (m * (segmentRelativeToLine * scaleCoefficient + line.getPoint1().x) + c);

                            double yPercentage = 1 - (((float) lowerBound - upperBound) - (calculatedY - upperBound)) / (lowerBound - upperBound);
                            data.setShowData(currentSegmentNumber, checkValue(yPercentage));
                        }
                    }
                }

                if (line.getSegmentStart() == currentSegmentNumber) {
                    if (!Objects.isNull(line.getPercentage1())) {
                        data.setShowData(currentSegmentNumber, checkValue(line.getPercentage1()));
                    }
                }
                if (line.getGetSegmentEnd() == currentSegmentNumber) {
                    if (!Objects.isNull(line.getPercentage2())) {
                        data.setShowData(currentSegmentNumber, checkValue(line.getPercentage2()));
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
            data.setShowData(currentSegmentNumber, true);
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


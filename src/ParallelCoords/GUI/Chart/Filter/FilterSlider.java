package ParallelCoords.GUI.Chart.Filter;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.GUI.Chart.FullLineData;
import ParallelCoords.GUI.Chart.PartialLineData;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class FilterSlider {
    DragBox upperSlider;
    DragBox lowerSlider;

    int upperY;
    int lowerY;
    int upperBound;
    int lowerBound;
    int currX;
    Color upperColour = new Color(157,78,0);
    Color lowerColour = new Color(0,112,209);
    int thickness = 3;
    ChartPanel panel;
    int segmentNumber;

    public FilterSlider(int upperBound, int lowerBound, int xPos, ChartPanel panel, int segmentNumber){
        this.panel = panel;
        this.segmentNumber = segmentNumber;
        upperSlider = new DragBox(xPos, upperBound-2, upperColour, thickness, this, true);
        lowerSlider = new DragBox(xPos, lowerBound+2, lowerColour, thickness, this, false);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        panel.add(upperSlider);
        panel.add(lowerSlider);
        setUpperBackgroundColour(Color.red);
    }

    public int getUpperY(){
        return upperSlider.getYPos();
    }

    public int getLowerY(){
        return lowerSlider.getYPos();
    }

    public int getUpperX(){
        return upperSlider.getXPos();
    }

    public int getLowerX(){
        return lowerSlider.getXPos();
    }

    public void setUpperY(int pos){
        upperSlider.setyPos(pos);
    }

    public void setLowerY(int pos){
        lowerSlider.setyPos(pos);
    }

    public void setUpperX(int pos){
        upperSlider.setxPos(pos);
    }

    public void setLowerX(int pos){
        lowerSlider.setxPos(pos);
    }

    public void setUpperBackgroundColour(Color color){
        upperSlider.setBackground(color);
    }
    public void setLowerBackgroundColour(Color color){
        lowerSlider.setBackground(color);
    }

    public void setLowerBorder(Color color, int thickness){
        upperSlider.setNewBorder(new LineBorder(color, thickness));
        this.thickness = thickness;
    }

    public void setUpperBound(int upperBound){
        this.upperBound = upperBound;
        upperSlider.setOuterBound(upperBound);
    }

    public void setLowerBound(int lowerBound){
        this.lowerBound = lowerBound;
        lowerSlider.setOuterBound(lowerBound);
    }

    public void remove(){
      panel.remove(upperSlider);
      panel.remove(lowerSlider);
    }

    public boolean checkValue(double value){
        int upperSliderPos = upperSlider.getRealYPos() - upperBound;
        int lowerSliderPos = lowerSlider.getRealYPos() - upperBound;
        double upperPercentage = 1 - (((float)lowerBound - upperBound) - upperSliderPos) / (lowerBound - upperBound);
        double lowerPercentage = 1 - (((float)lowerBound - upperBound) - lowerSliderPos) / (lowerBound - upperBound);
        System.out.println(upperPercentage);
        System.out.println(lowerPercentage);
        return !(value <= upperPercentage) && !(value >= lowerPercentage);
        //return !(value <= upperSliderPos) && !(value >= lowerSliderPos);

    }

    public void updateValues(){
        for (FullLineData data: panel.getFullLineData()) {
            for (PartialLineData line:data.getData()) {
                if (line.getSegmentStart() == segmentNumber){
                    if (!(Objects.isNull(line.getPercentage1()) || Objects.isNull(line.getPercentage2()))) {
                        if (!checkValue(line.getPercentage1())) {
                            data.setShowData(segmentNumber, false);
                        } else {
                            data.setShowData(segmentNumber, true);
                        }
                    }
                }

            }
        }
        updatePanel();
    }


    public void updatePanel(){
        panel.repaint();
        panel.updateUI();
    }

}


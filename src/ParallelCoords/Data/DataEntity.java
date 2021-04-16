package ParallelCoords.Data;

import java.awt.*;

public class DataEntity {
    private double value;
    private int index;
    private int columnID;
    private String textData;
    private boolean confirmedValue;
    private boolean isText;
    private Color lineColor;

    public int getIndex() {
        return index;
    }

    public double getValue() {
        return value;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public void setText(boolean text) {
        isText = text;
    }

    public boolean isText() {
        return isText;
    }

    public String getTextData() {
        return textData;
    }

    public void setConfirmedValue(boolean confirmedValue) {
        this.confirmedValue = confirmedValue;
    }

    public boolean isConfirmedValue() {
        return confirmedValue;
    }


    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public Color getLineColor() {
        return lineColor;
    }
    public double calculateMidValue(double left, double right){
        return ((left+right)/2);
    }

    public int getColumnID() {
        return columnID;
    }

    public void setColumnID(int columnID) {
        this.columnID = columnID;
    }


}

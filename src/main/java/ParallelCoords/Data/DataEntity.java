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

    public void setIndex(int index) {
        this.index = index;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isText() {
        return isText;
    }

    public void setText(boolean text) {
        isText = text;
    }

    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public boolean isConfirmedValue() {
        return confirmedValue;
    }

    public void setConfirmedValue(boolean confirmedValue) {
        this.confirmedValue = confirmedValue;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public double calculateMidValue(double left, double right) {
        return ((left + right) / 2);
    }

    public int getColumnID() {
        return columnID;
    }

    public void setColumnID(int columnID) {
        this.columnID = columnID;
    }


}

package ParallelCoords.Data;

public class DataEntity {
    private double value;
    private int index;
    private int columnID;
    private boolean confirmedValue;

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

    public void setConfirmedValue(boolean confirmedValue) {
        this.confirmedValue = confirmedValue;
    }

    public boolean isConfirmedValue() {
        return confirmedValue;
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

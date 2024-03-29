package ParallelCoords.Data;

import ParallelCoords.Settings.UserGraphSettings;
import ParallelCoords.Settings.UserSettings;

import java.util.ArrayList;

public class DataColumn {
    private boolean reversed = false;
    private boolean hidden = false;
    private int columnID;
    private int columnPosition;
    private String columnName;
    private boolean calculated = false;
    private double maxValue;
    private double minValue;


    private ArrayList<DataEntity> dataColumn = new ArrayList<>();

    public DataColumn(ArrayList<DataEntity> dataColumn, int index){
        this.dataColumn = dataColumn;
        this.columnID = index;
        this.columnPosition = index;
    }

    public DataColumn(int index){
        this.columnID = index;
        this.columnName = "Column " + (index + 1);
        this.columnPosition = index;
    }

    public DataColumn(int index, String name){
        this.columnID = index;
        this.columnName = name;
        this.columnPosition = index;
    }


    public double calculateMaxValue(){
        for (DataEntity data:dataColumn) {
            if (data.isConfirmedValue()) {
                maxValue = data.getValue();
                break;
            }
        }
        for (DataEntity data:dataColumn) {
            if (data.isConfirmedValue()) {
                if (data.getValue() > maxValue) {
                    maxValue = data.getValue();
                }
            }
        }
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double calculateMinValue(){
        for (DataEntity data:dataColumn) {
            if (data.isConfirmedValue()) {
                minValue = data.getValue();
                break;
            }
        }
        for (DataEntity data:dataColumn) {
            if (data.isConfirmedValue()) {
                if (data.getValue() < minValue) {
                    minValue = data.getValue();
                }
            }
        }
        return minValue;
    }

    public void setCalculated(boolean calculated) {
        this.calculated = calculated;
    }

    public double getValuePercentage(int index, boolean absolute, boolean inverted) {

        if (!calculated){
            calculateMaxValue();
            calculateMinValue();
        }

        if (!absolute){
            double range = maxValue - minValue;
            if (range == 0){
                return 0.5;
            }

            if (dataColumn.get(index).isConfirmedValue()){
                double adjusted = dataColumn.get(index).getValue() - minValue;

                if(inverted){
                    return adjusted / range;
                }
                else {
                    return 1 - adjusted / range;
                }
            }
            else{
                return -1;
            }
        }
        else{
            UserGraphSettings settings = UserSettings.getInstance().getUserGraphSettings();
            if (dataColumn.get(index).isConfirmedValue()){
                double value = dataColumn.get(index).getValue() - settings.getChartAxisMin();

                if(inverted){
                    return value / (settings.getChartAxisMax() - settings.getChartAxisMin());

                }
                else {
                    return 1 - value / (settings.getChartAxisMax() - settings.getChartAxisMin());

                }
            }
            else{
                return -1;
            }

        }



        //return 1 - adjusted/range;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public void setColumnPosition(int columnPosition) {
        this.columnPosition = columnPosition;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ArrayList<DataEntity> getColumnData() {
        return dataColumn;
    }

    public int getColumnSize() {
        return dataColumn.size();
    }

    public int getColumnIndex(){
        return getColumnSize()- 1;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnID = columnIndex;
    }

    private boolean checkIndex(int index){
        return index <= getColumnIndex() && index >= 0;
    }

    public boolean setValue(int index, DataEntity value) {
        if (checkIndex(index)){
            dataColumn.set(index, value);
            return true;
        }
        return false;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void addEntity(DataEntity entity){
        entity.setColumnID((columnID));
        entity.setIndex(dataColumn.size());
        dataColumn.add(entity);
    }

    public boolean addEntity(int index){
        if (checkIndex(index)){
            dataColumn.remove(index);
            return true;
        }
        return false;
    }

    public DataEntity findEntity(int index){
        for (DataEntity dataEntity : dataColumn) {
            if (dataEntity.getIndex() == index){
                return dataEntity;
            }
        }
        return null;
    }


    public boolean isReversed(){
        return reversed;
    }

    public int getColumnID() {
        return columnID;
    }

    public boolean isNumber(){
        for (DataEntity e: dataColumn) {
            if (e.isText()) {
                return false;
            }
        }
        return true;
    }
    public boolean isConfirmed(){

        for (DataEntity e: dataColumn) {
            if (!e.isConfirmedValue()) {
                return false;
            }
        }
        return true;
    }

}

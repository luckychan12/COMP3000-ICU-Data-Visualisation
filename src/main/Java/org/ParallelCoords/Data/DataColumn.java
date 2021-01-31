package org.ParallelCoords.Data;

import java.util.ArrayList;
import java.util.Collections;

public class DataColumn {
    private boolean reversed = false;
    private int columnID;
    private String columnName;
    private ArrayList<Double> dataColumn = new ArrayList<Double>();

    public DataColumn(ArrayList<Double> dataColumn, int index){
        this.dataColumn = dataColumn;
        this.columnID = index;
    }

    public DataColumn(int index){
        this.columnID = index;
    }

    public DataColumn(int index, String name){
        this.columnID = index;
        this.columnName = name;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ArrayList<Double> getColumn() {
        return dataColumn;
    }

    public int getColumnSize() {
        return dataColumn.size();
    }

    public int getColumnIndex(){
        return getColumnSize()- 1;
    }

    private boolean checkIndex(int index){
        return index <= getColumnIndex() && index >= 0;
    }

    public boolean setValue(int index, double value) {
        if (checkIndex(index)){
            dataColumn.set(index, value);
            return true;
        }
        return false;
    }

    public void addValue(double value){
        dataColumn.add(value);
    }

    public boolean removeValue(int index){
        if (checkIndex(index)){
            dataColumn.remove(index);
            return true;
        }
        return false;
    }

    public ArrayList<Double> reverseColumn() {
        Collections.reverse(dataColumn);
        reversed = !reversed;
        return dataColumn;
    }

    public boolean isReversed(){
        return reversed;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnID = columnIndex;
    }

    public int getColumnID() {
        return columnID;
    }
}

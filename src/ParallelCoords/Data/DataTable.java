package ParallelCoords.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Pattern;

public class DataTable {
    private int index;
    private String tableName;
    private final ArrayList<DataColumn> columns = new ArrayList<>();
    private int numRows;
    private double min;
    private double max;
    private boolean definedHeaders;
    Random rand = new Random();

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean hasDefinedHeaders() {
        return definedHeaders;
    }

    public void setDefinedHeaders(boolean definedHeaders) {
        this.definedHeaders = definedHeaders;
    }

    public int getIndex() {
        return index;
    }

    public ArrayList<DataColumn> GetData() {
        return columns;
    }

    public void addColumn(DataColumn entity){
        entity.setColumnIndex(columns.size());
        columns.add(entity);
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public DataColumn getColumn(int index){
        return columns.get(index);
    }

    public ArrayList<DataColumn> getAllColumns(){
        return columns;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumRows() {
        return numRows;
    }

    private boolean checkIndex(int index){
        return index <= (columns.size() -1) && index >= 0;
    }

    public ArrayList<DataEntity> getRecord(int index){
        ArrayList<DataEntity> record = new ArrayList<>();
        for (DataColumn column : columns) {
            record.add(column.findEntity(index));
        }
        return record;
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return Pattern.compile("-?\\d+(\\.\\d+)?").matcher(strNum).matches();
    }

    public int getNumberOfColumns(){
        return columns.size();
    }

    public double getNumValueAt(int row, int column){
        return columns.get(column).getColumnData().get(row).getValue();
    }
    public String getTextValueAt(int row, int column){
        return columns.get(column).getColumnData().get(row).getTextData();
    }


    public void setValueAt(String value, int row, int col){
        DataEntity val = columns.get(col).getColumnData().get(row);
        columns.get(col).setCalculated(false);
        if (value.equals("")){
            val.setText(true);
            val.setConfirmedValue(false);
            val.setTextData("NULL");
        }
        else if(!(isNumeric(value))) {
            val.setText(true);
            val.setConfirmedValue(false);
            val.setTextData(value);
        }
        else {
            val.setText(false);
            val.setConfirmedValue(true);
            val.setValue(Double.parseDouble(value));
        }
    }

    public void sortColumnsByPosition(){
        int n = columns.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++){
                if (columns.get(j).getColumnPosition() > columns.get(j +1).getColumnPosition())
                {
                    Collections.swap(columns, j, j+1);
                }
            }
    }


    public void sortColumnsByIndex(){
        int n = columns.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++){
                if (columns.get(j).getColumnIndex() > columns.get(j +1).getColumnIndex())
                {
                    Collections.swap(columns, j, j+1);
                }
            }
    }

    public Color genColour(){
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b).darker();
    }

    public int getMaxSize(){
        int max = 0;
        for (DataColumn column :columns) {
            if (column.getColumnSize() > max){
                max = column.getColumnSize();
            }
        }
        return max;
    }

    public boolean removeColumn(int index) {
        if (checkIndex(index)){
            columns.remove(index);
            return true;
        }
        return false;
    }
}

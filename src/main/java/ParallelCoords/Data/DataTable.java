package ParallelCoords.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class DataTable {
    private ArrayList<DataColumn> columns;
    private String tableName;
    private ArrayList<Boolean> showRecord;
    private int numRows;
    private boolean definedHeaders;
    private boolean dismissWarning = false;

    public DataTable(){}

    public DataTable(int numRows, boolean definedHeaders, ArrayList<DataColumn> columns){
        this.numRows = numRows;
        this.definedHeaders = definedHeaders;
        this.columns = columns;
    }

    public boolean hasDefinedHeaders() {
        return definedHeaders;
    }

    public void setDefinedHeaders(boolean definedHeaders) {
        this.definedHeaders = definedHeaders;
    }

    public ArrayList<DataColumn> getData() {
        return columns;
    }

    public void addColumn(DataColumn entity) {
        if (columns == null){
            columns = new ArrayList<>();
        }
        entity.setColumnIndex(columns.size());
        columns.add(entity);
    }

    public void setDismissWarning(boolean dismissWarning) {
        this.dismissWarning = dismissWarning;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public DataColumn getColumn(int index) {
        return columns.get(index);
    }


    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    private boolean checkIndex(int index) {
        return index <= (columns.size() - 1) && index >= 0;
    }

    public void initShowRecordList() {
        showRecord = new ArrayList<>(Collections.nCopies(getMaxSize(), true));
    }


    public ArrayList<DataEntity> getRecord(int index) {
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
        return Pattern.compile("-?\\d+(\\.\\d+)?").matcher(strNum).matches() || Pattern.compile("-?\\.\\d+").matcher(strNum).matches();
    }

    public int getNumberOfColumns() {
        return columns.size();
    }

    public double getNumValueAt(int row, int column) {
        return columns.get(column).getColumnData().get(row).getValue();
    }

    public String getTextValueAt(int row, int column) {
        return columns.get(column).getColumnData().get(row).getTextData();
    }

    public void setValueAt(String value, int row, int col) {
        DataEntity val = columns.get(col).getColumnData().get(row);
        columns.get(col).setCalculated(false);
        if (value.equals("")) {
            val.setText(true);
            val.setConfirmedValue(false);
            val.setTextData("NULL");
        } else if (!(isNumeric(value))) {
            val.setText(true);
            val.setConfirmedValue(false);
            val.setTextData(value);
        } else {
            val.setText(false);
            val.setConfirmedValue(true);
            val.setValue(Double.parseDouble(value));
        }
    }

    public void setColumns(ArrayList<DataColumn> columns){
        this.columns = columns;
    }


    public int getMaxSize() {
        int max = 0;
        for (DataColumn column : columns) {
            if (column.getColumnSize() > max) {
                max = column.getColumnSize();
            }
        }
        return max;
    }

    public boolean removeColumn(int index) {
        if (checkIndex(index)) {
            columns.remove(index);
            return true;
        }
        return false;
    }


}

package ParallelCoords.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class DataTable {
    private final ArrayList<DataColumn> columns = new ArrayList<>();
    private int index;
    private String tableName;
    private ArrayList<Boolean> showRecord;
    private int numRows;
    private double min;
    private double max;
    private boolean definedHeaders;
    private boolean dismissWarning = false;

    public boolean hasDefinedHeaders() {
        return definedHeaders;
    }

    public void setDefinedHeaders(boolean definedHeaders) {
        this.definedHeaders = definedHeaders;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<DataColumn> GetData() {
        return columns;
    }

    public void addColumn(DataColumn entity) {
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

    public ArrayList<DataColumn> getAllColumns() {
        return columns;
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

    public void setShowRecordValue(int i, Boolean bool) {
        showRecord.set(i, bool);
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

    public void sortColumnsByPosition() {
        int n = columns.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++) {
                if (columns.get(j).getColumnPosition() > columns.get(j + 1).getColumnPosition()) {
                    Collections.swap(columns, j, j + 1);
                }
            }
    }


    public void sortColumnsByIndex() {
        int n = columns.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++) {
                if (columns.get(j).getColumnIndex() > columns.get(j + 1).getColumnIndex()) {
                    Collections.swap(columns, j, j + 1);
                }
            }
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

    public ArrayList<Boolean> getShowRecord() {
        return showRecord;
    }

    public boolean removeColumn(int index) {
        if (checkIndex(index)) {
            columns.remove(index);
            return true;
        }
        return false;
    }
}

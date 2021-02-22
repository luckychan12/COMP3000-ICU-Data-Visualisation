package ParallelCoords.Data;

import java.util.ArrayList;
import java.util.Collections;

public class DataColumn {
    private boolean reversed = false;
    private int columnID;
    private String columnName;
    private ArrayList<DataEntity> dataColumn = new ArrayList<DataEntity>();

    public DataColumn(ArrayList<DataEntity> dataColumn, int index){
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

    public ArrayList<DataEntity> getColumnData() {
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

    public boolean setValue(int index, DataEntity value) {
        if (checkIndex(index)){
            dataColumn.set(index, value);
            return true;
        }
        return false;
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

    public ArrayList<DataEntity> reverseColumn() {
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

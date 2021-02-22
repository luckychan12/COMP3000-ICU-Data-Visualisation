package ParallelCoords.Data;

import java.util.ArrayList;

public class DataTable {
    private int index;
    private final ArrayList<DataColumn> columns = new ArrayList<DataColumn>();

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

    public DataColumn getColumn(int index){
        return columns.get(index);
    }


    private boolean checkIndex(int index){
        return index <= (columns.size() -1) && index >= 0;
    }

    public ArrayList<DataEntity> getRecord(int index){
        ArrayList<DataEntity> record = new ArrayList<DataEntity>();
        for (DataColumn column : columns) {
            record.add(column.findEntity(index));
        }
        return record;
    }

    public boolean removeColumn(int index) {
        if (checkIndex(index)){
            columns.remove(index);
            return true;
        }
        return false;
    }
}

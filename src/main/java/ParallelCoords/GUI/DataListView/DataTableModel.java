package ParallelCoords.GUI.DataListView;

import ParallelCoords.Data.DataEntity;
import ParallelCoords.Data.DataTable;

import javax.swing.table.AbstractTableModel;

public class DataTableModel extends AbstractTableModel {
    private final DataTable dataTable;

    public DataTableModel(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    @Override
    public Class<?> getColumnClass(int arg0) {
        return String.class;
    }

    @Override
    public int getColumnCount() {
        return dataTable.getNumberOfColumns();
    }

    @Override
    public int getRowCount() {
        if (dataTable.hasDefinedHeaders()) {
            return dataTable.getNumRows() - 1;
        } else {
            return dataTable.getNumRows() + 1;
        }

    }

    @Override
    public String getColumnName(int columnIndex) {
        if (dataTable.hasDefinedHeaders()) {
            return dataTable.getColumn(columnIndex).getColumnName();
        } else {
            return "Column " + (columnIndex + 1);
        }
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return Integer.toString(rowIndex);
        } else {
            columnIndex = columnIndex - 1;
        }

        DataEntity entity = dataTable.getAllColumns().get(columnIndex).getColumnData().get(rowIndex);

        if (entity.isText()) {
            return dataTable.getTextValueAt(rowIndex, columnIndex);
        } else if (entity.isConfirmedValue()) {
            return Double.toString(dataTable.getNumValueAt(rowIndex, columnIndex));
        } else {
            return "NULL";
        }
    }

    @Override
    public void setValueAt(Object arg0, int rowIndex, int columnIndex) {
        System.out.println("Fired");
        dataTable.setValueAt((String) arg0, rowIndex, columnIndex - 1);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }
}

package ParallelCoords.GUI.DataView;

import ParallelCoords.Data.Data;
import ParallelCoords.Data.DataColumn;
import ParallelCoords.Data.DataTable;
import ParallelCoords.Main;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.util.Collection;
import java.util.Collections;

public class DataColumnModel extends DefaultTableColumnModel {
    private Main mainWindow;

    public DataColumnModel(Main mainWindow) {
        super();
        this.mainWindow = mainWindow;
        this.setColumnMargin(1);
    }

    public boolean getColumnSelectionAllowed() {
        return false;
    }

    public void moveColumn(int src, int tar) {
        if (src != 0 && tar != 0) {
            super.moveColumn(src, tar);
            if (src != tar) {
                Data data = Data.getInstance();
                DataTable dataTable = data.getDataStore().get(data.getCurrID());
                dataTable.sortColumnsByPosition();
                Collections.swap(dataTable.getAllColumns(), src -1, tar -1);
            }
        }
    }

    public void removeColumn(TableColumn arg0) {
    }
}

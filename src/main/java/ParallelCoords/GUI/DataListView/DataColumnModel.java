package ParallelCoords.GUI.DataListView;

import ParallelCoords.Data.Data;
import ParallelCoords.Data.DataTable;
import ParallelCoords.Main;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.util.Collections;

public class DataColumnModel extends DefaultTableColumnModel {
    private final Main mainWindow;

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
                //dataTable.sortColumnsByPosition();


                //System.out.println(src + ", " + tar);
                //System.out.println(dataTable.getColumn(0).findEntity(src -1).getValue() + ", " + dataTable.getColumn(0).findEntity(tar -1).getValue());

                Collections.swap(dataTable.getAllColumns(), src - 1, tar - 1);
                for (int i = 0; i < this.getColumnCount(); i++) {
                    //System.out.println(this.getColumn(i).getHeaderValue().toString());
                    this.getColumn(i).setModelIndex(i);
                }
                mainWindow.repaintCharts(false);
            }
        }
    }


    public void removeColumn(TableColumn arg0) {
    }
}

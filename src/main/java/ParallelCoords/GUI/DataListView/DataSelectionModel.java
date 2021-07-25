package ParallelCoords.GUI.DataListView;

import ParallelCoords.Data.Data;
import ParallelCoords.Data.DataTable;
import ParallelCoords.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DataSelectionModel extends DefaultListSelectionModel implements ListSelectionListener {

    private Main mainWindow;

    public DataSelectionModel(Main mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Data data = Data.getInstance();
        DataTable dataTable = data.getDataStore().get(data.getCurrID());
        int[] selection = this.mainWindow.getDataDisplayTable().getDisplayTable().getSelectedRows();
        for (int i : selection) {
            System.out.println(i);
        }
        this.mainWindow.repaintCharts(true);
    }
}
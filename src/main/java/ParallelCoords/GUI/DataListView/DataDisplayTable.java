package ParallelCoords.GUI.DataListView;

import ParallelCoords.Data.Data;
import ParallelCoords.Data.DataTable;
import ParallelCoords.Main;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

public class DataDisplayTable extends JPanel {
    private Main mainWindow;
    private JTable displayTable;

    public DataDisplayTable(Main mainWindow){
        super();
        this.mainWindow = mainWindow;
        this.setLayout(new GridLayout(1, 1));
        setup();
    }

    public void setup(){
        Data data = Data.getInstance();
        DataTable dataTable =  data.getDataStore().get(data.getCurrID());
        this.removeAll();
        DataColumnModel tableColumnModel = new DataColumnModel(this.mainWindow);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DataCellEditor cellEditor = new DataCellEditor();
        TableColumn idCol = new TableColumn(0, 30, cellRenderer, cellEditor);
        idCol.setHeaderValue("#");
        idCol.setResizable(true);
        tableColumnModel.addColumn(idCol);

        TableColumn[] cols = new TableColumn[dataTable.getNumberOfColumns() + 2];
        for (int colIndex = 1; colIndex <= dataTable.getNumberOfColumns(); colIndex++) {
            cols[colIndex] = new TableColumn(colIndex, 100, cellRenderer, cellEditor);
            cols[colIndex].setHeaderValue(dataTable.getAllColumns().get(colIndex-1).getColumnName());
            cols[colIndex].setResizable(true);
            tableColumnModel.addColumn(cols[colIndex]);
        }

        DataTableModel dataSheetTableModel = new DataTableModel(dataTable);
        this.displayTable = new JTable(dataSheetTableModel, tableColumnModel);
        this.displayTable.setFont( new Font("Calibri", Font.PLAIN, UserSettings.getInstance()
                .getUserGeneralSettings().getTableFontSize()));
        this.displayTable.getTableHeader().setFont(new Font("Calibri", Font.BOLD, UserSettings.getInstance()
                .getUserGeneralSettings().getTableHeaderFontSize()));
        this.displayTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.displayTable.setShowGrid(true);
        this.displayTable.setColumnSelectionAllowed(false);
        this.displayTable.setRowSelectionAllowed(true);
        this.displayTable.getSelectionModel().addListSelectionListener(new DataSelectionModel(mainWindow));

        // Embed Table in ScrollPane

        JScrollPane scrollPane = new JScrollPane(this.displayTable);

        this.setLayout(new GridLayout(1, 1));
        this.add(scrollPane);
    }

    public JTable getDisplayTable() {
        return displayTable;
    }

    void setSelectedRows(List<Integer> selection){
        displayTable.clearSelection();
        for(Integer s : selection){
            displayTable.getSelectionModel().addSelectionInterval(s, s);
        }
    }
}

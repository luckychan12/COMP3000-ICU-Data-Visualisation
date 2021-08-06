package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.Data.DataColumn;
import ParallelCoords.Data.DataTable;
import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.GUI.TableMenuBar.Listeners.EmptyFieldsException;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.IllegalFormatWidthException;
import java.util.Objects;

public class ColumnDataMenu extends JMenu {
    private DataColumn column;
    private ArrayList<JMenuItem> items = new ArrayList<>();
    private boolean inverted = false;
    private ChartPanel panel;
    public ColumnDataMenu(DataColumn column, ChartPanel panel){
        this.column = column;
        this.panel = panel;
    }

    public DataColumn getColumn() {
        return column;
    }

    public void editName(){
        String input = JOptionPane.showInputDialog(null, "Edit column name:",
                column.getColumnName());
        try {
            if (input == null || ("".equals(input))) {
                throw new EmptyFieldsException();
            }
            if (input.length() > 48) {
                throw new IllegalFormatWidthException(input.length());
            }

            column.setColumnName(input);
        } catch (final IllegalFormatWidthException e) {
            JOptionPane.showMessageDialog(null, "The name you have set is too long!", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (EmptyFieldsException ignored) {
        }
    }

    public void invert(){
        inverted = !inverted;
        panel.rePrepData(false, false);
    }

    public void toggleHidden(){
        column.setHidden(!column.isHidden());
        int i = 0;
        ArrayList<ColumnDataMenu> menus = panel.getDataMenus();
        DataTable table = panel.getDataTable();
        ArrayList<DataColumn> columns = new ArrayList<>();
        for (DataColumn col: table.getData()) {
            if (!menus.get(i).isHidden()){
                columns.add(col);
            }
            i++;
        }
        if(column.isHidden()){
            items.get(2).setText("Show data column");
        }
        else {
            items.get(2).setText("Hide data column");
        }
        panel.setDataTableMask(new DataTable(table.getNumRows(), table.hasDefinedHeaders(), columns));
        panel.createFilterSliders();
        panel.rePrepData(false,false);

    }

    public boolean isHidden() {
        return column.isHidden();
    }

    public void sortColours(){
        int index = -1;
        for (int i = 0; i < panel.getDataTableMask().getData().size(); i++) {
            if (Objects.equals(panel.getDataTableMask().getData().get(i), column)) {
                index = i;
            }
        }
        if (index != -1) {
            panel.columnColour(index);
        }
    }


    public boolean isInverted() {
        return inverted;
    }

    public void setColumn(DataColumn column) {
        this.column = column;
    }

    public ArrayList<JMenuItem> getItems() {
        return items;
    }

    public void reloadFonts(){
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);
        for (JMenuItem item:items) {
            item.setFont(font);
        }
    }
}

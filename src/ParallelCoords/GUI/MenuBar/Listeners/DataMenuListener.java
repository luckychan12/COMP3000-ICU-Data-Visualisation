package ParallelCoords.GUI.MenuBar.Listeners;
import ParallelCoords.Data.Data;
import ParallelCoords.Data.DataColumn;
import ParallelCoords.Data.DataEntity;
import ParallelCoords.Data.DataTable;
import ParallelCoords.Main;
import ParallelCoords.Settings.UserImportSettings;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class DataMenuListener {
    private Main main;
    private UserImportSettings settings = UserSettings.getInstance().getUserImportSettings();
    public DataMenuListener(Main mainWindow) {this.main = mainWindow;}

    public void importDataHeaders(ActionEvent e){
        importData(true);
    }
    public void importDataNoHeaders(ActionEvent e){
        importData(false);
    }

    public void importData(boolean hasHeaders){
        String filepath;
        JFileChooser chooser = new JFileChooser(settings.getLastOpenedFile());
        int returnVal = chooser.showOpenDialog(main);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filepath = chooser.getSelectedFile().getAbsolutePath();
            settings.setLastOpenedFile(filepath);
            UserSettings.getInstance().getUserImportSettings().setLastOpenedFile(chooser.getSelectedFile().getAbsolutePath());
            Data data = Data.getInstance();
            try {
                data.createData(main, filepath, hasHeaders);
                DataTable currData = data.getDataStore().get(data.getCurrID());
                currData.setTableName(chooser.getSelectedFile().getName());
                main.setData();



                //TODO TESTING DATA READ HERE
                ArrayList<DataColumn> cols = data.getDataStore().get(0).getAllColumns();
                for (DataColumn col :cols) {
                    System.out.print(col.getColumnName() + ", ");
                }
                System.out.println();
                for (int i = 0; i < cols.get(0).getColumnData().size(); i++) {
                    for (DataColumn col : cols) {
                        DataEntity ent = col.getColumnData().get(i);
                        if (ent.isConfirmedValue()) {
                            System.out.print(ent.getValue());
                        } else if (ent.isText()) {
                            System.out.print(ent.getTextData());
                        }
                        else {
                            System.out.print("NULL");
                        }

                        System.out.print(", ");
                    }
                    System.out.println();
                }


            }
            catch (Exception err){
                err.printStackTrace();
            }
        }

    }
}

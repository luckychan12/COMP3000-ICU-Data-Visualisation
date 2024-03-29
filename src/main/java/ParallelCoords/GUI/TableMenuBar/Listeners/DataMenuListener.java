package ParallelCoords.GUI.TableMenuBar.Listeners;

import ParallelCoords.Data.Data;
import ParallelCoords.Data.DataTable;
import ParallelCoords.Main;
import ParallelCoords.Settings.UserImportSettings;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DataMenuListener {
    private final Main main;
    private final UserImportSettings settings = UserSettings.getInstance().getUserImportSettings();

    public DataMenuListener(Main mainWindow) {
        this.main = mainWindow;
    }

    public void importDataHeaders(ActionEvent e) {
        importData(true);
    }

    public void importDataNoHeaders(ActionEvent e) {
        importData(false);
    }

    public void importData(boolean hasHeaders) {
        String filepath;
        JFileChooser chooser = new JFileChooser(settings.getLastOpenedFile());
        int returnVal = chooser.showOpenDialog(main);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filepath = chooser.getSelectedFile().getAbsolutePath();
            settings.setLastOpenedFile(filepath);
            UserSettings.getInstance().getUserImportSettings().setLastOpenedFile(chooser.getSelectedFile().getAbsolutePath());
            Data data = Data.getInstance();
            try {
                data.createData(filepath, hasHeaders);
                DataTable currData = data.getDataStore().get(data.getCurrID());
                currData.setTableName(chooser.getSelectedFile().getName());
                main.setData();
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "An error occurred while loading the dataset", "Error loading data", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    public void setDelimiter(ActionEvent e) {
        String s = JOptionPane.showInputDialog(
                main,
                "Set delimiter:\n" + "The current delimiter is: '" + settings.getDelimiter() + "'",
                UserSettings.getInstance().getUserImportSettings().getDelimiter());

        if ((s != null) && (s.length() > 0)) {
            settings.setDelimiter(s);
        }
    }
}

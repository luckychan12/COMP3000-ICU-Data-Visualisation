package org.ParallelCoords.Listeners;

import org.ParallelCoords.Data.Data;
import org.ParallelCoords.Main;
import org.ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DataMenuListener {
    private Main main;
    public DataMenuListener(Main mainWindow) {this.main = mainWindow;}

    public void importData(ActionEvent e){
        String filepath;
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(main);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filepath = chooser.getSelectedFile().getAbsolutePath();
            UserSettings.getInstance().getUserImportSettings().setLastOpenedFile(chooser.getSelectedFile().getAbsolutePath());
            Data data = Data.getInstance();
            try {
                data.createData(main, filepath, false);

            }
            catch (Exception err){
                System.out.println("err");
            }

        }

    }
}

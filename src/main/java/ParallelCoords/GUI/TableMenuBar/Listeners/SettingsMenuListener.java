package ParallelCoords.GUI.TableMenuBar.Listeners;

import ParallelCoords.Main;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SettingsMenuListener {
    private final Main mainWindow;
    public SettingsMenuListener(Main mainWindow) {
        this.mainWindow = mainWindow;
    }
    public UserSettings userSettings =  UserSettings.getInstance();
    public void setDelimiter(ActionEvent e) {
        String s = JOptionPane.showInputDialog(
                mainWindow,
                "Set delimiter:\n" + "The Current delimiter is: '" + userSettings.getUserImportSettings().getDelimiter() + "'",
                "Set Delimiter",
                JOptionPane.PLAIN_MESSAGE);

        if ((s != null) && (s.length() > 0)) {
            userSettings.getUserImportSettings().setDelimiter(s);
        }
    }
}

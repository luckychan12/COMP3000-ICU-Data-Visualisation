package ParallelCoords.Settings;

import javax.swing.*;

public class UserGeneralSettings {
    public int getGeneralFontSize() {
        String defaultVal = "16";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("GeneralFontSize", defaultVal));
        } catch (NumberFormatException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nfont size from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setGeneralFontSize(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setGeneralFontSize(int value) {
        UserSettings.getInstance().updateProperties("GeneralFontSize", Integer.toString(value));
    }

    public int getTableFontSize() {
        String defaultVal = "16";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("TableFontSize", defaultVal));
        } catch (NumberFormatException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nfont size from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setTableFontSize(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setTableFontSize(int value) {
        UserSettings.getInstance().updateProperties("TableFontSize", Integer.toString(value));
    }

    public int getTableHeaderFontSize() {
        String defaultVal = "16";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("TableHeaderFontSize", defaultVal));
        } catch (NumberFormatException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nfont size from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setTableHeaderFontSize(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setTableHeaderFontSize(int value) {
        UserSettings.getInstance().updateProperties("TableHeaderFontSize", Integer.toString(value));
    }


}

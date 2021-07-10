package ParallelCoords.Settings;

import javax.swing.*;

public class UserGeneralSettings {
    public void setGeneralFontSize(int value){
        UserSettings.getInstance().updateProperties("GeneralFontSize", Integer.toString(value));
    }

    public int getGeneralFontSize(){
        String defaultVal = "14";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("GeneralFontSize", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nfont size from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setGeneralFontSize(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }




}

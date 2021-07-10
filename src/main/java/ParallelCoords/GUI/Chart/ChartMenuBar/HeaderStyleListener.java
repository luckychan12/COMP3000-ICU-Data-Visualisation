package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;

public class HeaderStyleListener {
    private JFrame messageFrame = new JFrame();

    public void setStaggered(ChartPanel panel){
        UserSettings.getInstance().getUserGraphSettings().setHeaderDisplayType("Staggered");
        panel.changeHeaderStyle();
    }

    public void setTilted(ChartPanel panel){
        UserSettings.getInstance().getUserGraphSettings().setHeaderDisplayType("Tilted");
        panel.changeHeaderStyle();
    }

    public void setAngle(ChartPanel panel){
        String input= JOptionPane.showInputDialog(messageFrame,"Enter angle for tilted header display mode",
                Integer.toString(UserSettings.getInstance().getUserGraphSettings().getTiltedAngle()));
        try {
            int intInput = Integer.parseInt(input);
            if (intInput < 0 || intInput > 90){
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGraphSettings().setTiltedAngle(intInput);
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame,"Invalid input value. Please select an Integer value between 0 and 90", "Error", JOptionPane.WARNING_MESSAGE );
        }

    }

    public void setTiltedPadding(ChartPanel panel){
        String input= JOptionPane.showInputDialog(messageFrame,"Enter the percentage of screen space allocated \nfor header text above the graph (Max 50%):",
                Integer.toString(UserSettings.getInstance().getUserGraphSettings().getTiltedTopPadding()));
        try {
            int intInput = Integer.parseInt(input);
            if (intInput < 0 || intInput > 50){
                throw new NumberFormatException();
            }
            UserSettings.getInstance().getUserGraphSettings().setTiltedTopPaddingPercentage(intInput);
            panel.changeHeaderStyle();
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame,"Invalid input value. Please select an Integer value between 0 and 50", "Error", JOptionPane.WARNING_MESSAGE );
        }

    }

}

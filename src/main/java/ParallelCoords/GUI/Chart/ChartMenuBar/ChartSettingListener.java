package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.GUI.Chart.Filter.FilterSlider;
import ParallelCoords.GUI.TableMenuBar.Listeners.EmptyFieldsException;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;

public class ChartSettingListener {
    private final JFrame messageFrame = new JFrame();
    private ChartPanel panel;

    ChartSettingListener(ChartPanel panel) {
        this.panel = panel;
    }

    public void setTicks() {
        String input;
        input = JOptionPane.showInputDialog(messageFrame, "Enter number of ticks to be displayed on graph",
                Integer.toString(UserSettings.getInstance().getUserGraphSettings().getChartNumTicks()));
        try {
            if (input == null || ("".equals(input))) {
                throw new EmptyFieldsException();
            }
            int intInput = Integer.parseInt(input);
            if (intInput < 1 || intInput > 100) {
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGraphSettings().setChartNumTicks(intInput);
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame, "Invalid input value. Please select an Integer value between 0 and 30", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (EmptyFieldsException ignored) {
        }

    }

    public void toggleFilter() {
        boolean tog = UserSettings.getInstance().getUserGraphSettings().getChartFilterTextData();
        UserSettings.getInstance().getUserGraphSettings().setChartFilterTextData(!tog);
        for (FilterSlider slider : panel.getFilterSliders()) {
            slider.updateValues();
        }
    }

}





package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.GUI.TableMenuBar.Listeners.EmptyFieldsException;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;

public class ChartSettingListener {
    private JFrame messageFrame = new JFrame();
    public void setTicks(ChartPanel panel){
        String input;
        input = JOptionPane.showInputDialog(messageFrame,"Enter number of ticks to be displayed on graph",
                Integer.toString(UserSettings.getInstance().getUserGraphSettings().getChartNumTicks()));
        try {
            if(input == null || ("".equals(input)))
            {
                throw new EmptyFieldsException();
            }
            int intInput = Integer.parseInt(input);
            if (intInput < 0 || intInput > 50){
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGraphSettings().setChartNumTicks(intInput);
        }
        catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame,"Invalid input value. Please select an Integer value between 0 and 30", "Error", JOptionPane.WARNING_MESSAGE );
        }
        catch (EmptyFieldsException ignored) { }

    }
}

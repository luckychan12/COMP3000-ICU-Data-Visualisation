package ParallelCoords.GUI.Chart;

import ParallelCoords.GUI.TableMenuBar.Listeners.EmptyFieldsException;
import ParallelCoords.Settings.UserGraphSettings;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;

public class ChartAxisSettingPane {
    private ChartPanel chartPanel;

    public ChartAxisSettingPane(ChartPanel chartPanel){
        this.chartPanel = chartPanel;
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        UserGraphSettings settings = UserSettings.getInstance().getUserGraphSettings();
        Font font = new Font("Calibri", Font.BOLD, fontSize );

        JTextField minInput = new JTextField(5);
        JTextField maxInput = new JTextField(5);

        minInput.setText(Double.toString(settings.getChartAxisMin()));
        maxInput.setText(Double.toString(settings.getChartAxisMax()));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel sub1 = new JPanel();
        JLabel label = new JLabel("Set range of displayed values to be used in 'Absolute' viewing mode");
        label.setFont(font);
        sub1.add(label);


        JPanel subPanel = new JPanel();
        subPanel.add(new JLabel("Red:"));
        subPanel.add(minInput);
        subPanel.add(Box.createHorizontalStrut(20)); // a spacer
        subPanel.add(new JLabel("Green:"));
        subPanel.add(maxInput);

        panel.add(sub1);
        panel.add(subPanel);

        Object[] options = { "Save",
                "Cancel" };
        int result = JOptionPane.showOptionDialog(null, panel,
                "Set axis range", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        System.out.println(result);
        if (result == 0) {
            checkNumInput(minInput.getText(), maxInput.getText());
        }

    }

    private void checkNumInput(String min, String max ) {
        try {
            if(min == null || ("".equals(min)) || max == null || ("".equals(max)) )
            {
                throw new EmptyFieldsException();
            }
            double parsedMin = Double.parseDouble(min);
            double parsedMax = Double.parseDouble(max);

            if (parsedMax <= parsedMin){
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGraphSettings().setChartAxisMax(parsedMax);
            UserSettings.getInstance().getUserGraphSettings().setChartAxisMin(parsedMin);
            chartPanel.rePrepData(true, true);
        }
        catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Invalid input value. \nPlease select values such that Min is greater than Max",
                    "Error", JOptionPane.WARNING_MESSAGE );
        }
        catch (EmptyFieldsException ignored) { }
    }
}

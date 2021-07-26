package ParallelCoords.GUI.Chart;

import ParallelCoords.Settings.UserGraphSettings;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;

public class ChartColourSettings {
    public ChartColourSettings() {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        UserGraphSettings settings = UserSettings.getInstance().getUserGraphSettings();
        Font font = new Font("Calibri", Font.BOLD, fontSize);

        JTextField rWeight = new JTextField(5);
        JTextField gWeight = new JTextField(5);
        JTextField bWeight = new JTextField(5);

        rWeight.setText(Float.toString(settings.getChartRedWeight()));
        gWeight.setText(Float.toString(settings.getChartGreenWeight()));
        bWeight.setText(Float.toString(settings.getChartBlueWeight()));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel sub1 = new JPanel();
        JLabel label = new JLabel("Set weights for each colour (Values must be between 0 an 5)");
        label.setFont(font);
        sub1.add(label);
        JPanel sub2 = new JPanel();
        JLabel label2 = new JLabel("Influences the random colour generation (Default = 0) (Increase for more colour)");
        label2.setFont(new Font("Calibri", Font.PLAIN, fontSize));
        sub2.add(label2);

        JPanel subPanel = new JPanel();
        subPanel.add(new JLabel("Red:"));
        subPanel.add(rWeight);
        subPanel.add(Box.createHorizontalStrut(20)); // a spacer
        subPanel.add(new JLabel("Green:"));
        subPanel.add(gWeight);
        subPanel.add(Box.createHorizontalStrut(20)); // a spacer
        subPanel.add(new JLabel("Blue:"));
        subPanel.add(bWeight);
        panel.add(sub1);
        panel.add(sub2);
        panel.add(subPanel);

        Object[] options = {"Save", "Reset",
                "Cancel"};
        int result = JOptionPane.showOptionDialog(null, panel,
                "Set Colour Weights", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        System.out.println(result);
        if (result == 0) {
            checkValue(rWeight.getText(), gWeight.getText(), bWeight.getText());
        }
        if (result == 1) {
            checkValue("0", "0", "0");
        }

    }


    private void checkValue(String r, String g, String b) {
        try {
            float checkedR = checkNumInput(r);
            float checkedG = checkNumInput(g);
            float checkedB = checkNumInput(b);
            UserSettings.getInstance().getUserGraphSettings().setChartRedWeight(checkedR);
            UserSettings.getInstance().getUserGraphSettings().setChartGreenWeight(checkedG);
            UserSettings.getInstance().getUserGraphSettings().setChartBlueWeight(checkedB);
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid input value. Please select an number between 0 and 5.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private float checkNumInput(String r) {
        float input = Float.parseFloat(r);
        if (input < 0 || input > 5) {
            throw new NumberFormatException();
        }
        return input;
    }
}


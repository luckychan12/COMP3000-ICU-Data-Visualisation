package ParallelCoords.GUI.TableMenuBar;

import ParallelCoords.Settings.UserGraphSettings;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChartColourSettings {

    private float fValue1, fValue2, fValue3;
    private int iValue1, iValue2, iValue3;

    public ChartColourSettings(String text1, String text2, String text3, int min, int max, boolean isLine) {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        UserGraphSettings settings = UserSettings.getInstance().getUserGraphSettings();
        Font font = new Font("Calibri", Font.BOLD, fontSize);

        JTextField rWeight = new JTextField(5);
        JTextField gWeight = new JTextField(5);
        JTextField bWeight = new JTextField(5);

        if(isLine) {
            rWeight.setText(Float.toString(settings.getChartColourWeights()[0]));
            gWeight.setText(Float.toString(settings.getChartColourWeights()[1]));
            bWeight.setText(Float.toString(settings.getChartColourWeights()[2]));
        }
        else {
            rWeight.setText(Integer.toString(settings.getFilterColour()[0]));
            gWeight.setText(Integer.toString(settings.getFilterColour()[1]));
            bWeight.setText(Integer.toString(settings.getFilterColour()[2]));
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel sub1 = new JPanel();
        JLabel label = new JLabel(text1);
        label.setFont(font);
        sub1.add(label);
        JPanel sub2 = new JPanel();
        JLabel label2 = new JLabel(text2);
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
                text3, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if (result == 0) {
            if (isLine){
                checkFloatValue(rWeight.getText(), gWeight.getText(), bWeight.getText(), min, max);
            }
            else {
                checkIntValue(rWeight.getText(), gWeight.getText(), bWeight.getText(), min, max);
            }

        }
        if (result == 1) {
            if (isLine){
                checkFloatValue("0","0","0", min, max);
            }
            else {
                checkIntValue("165","165","165", min, max);
            }
        }

    }


    private void checkFloatValue(String r, String g, String b, int min, int max) {
        try {
            fValue1 = checkFloatInput(r,min,max);
            fValue2 = checkFloatInput(g,min,max);
            fValue3 = checkFloatInput(b,min,max);
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid input value. Please select an number between " + min +" and "+ max +".", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void checkIntValue(String r, String g, String b, int min, int max) {
        try {
            iValue1 = checkIntInput(r,min,max);
            iValue2 = checkIntInput(g,min,max);
            iValue3 = checkIntInput(b,min,max);
        } catch (final NumberFormatException e) {
            iValue1 = checkIntInput("165",min,max);
            iValue2 = checkIntInput("165",min,max);
            iValue3 = checkIntInput("165",min,max);
            JOptionPane.showMessageDialog(new JFrame(), "Invalid input value. Please select an number between " + min +" and "+ max +".", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private float checkFloatInput(String r, int min, int max) {
        float input = Float.parseFloat(r);
        if (input < min || input > max) {
            throw new NumberFormatException();
        }
        return input;
    }

    private int checkIntInput(String r, int min, int max) {
        int input = Integer.parseInt(r);
        if (input < min || input > max) {
            throw new NumberFormatException();
        }
        return input;
    }

    public ArrayList<Integer> getIntValues() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(iValue1);
        list.add(iValue2);
        list.add(iValue3);
        return list;
    }

    public ArrayList<Float> getFloatValues() {
        ArrayList<Float> list = new ArrayList<>();
        list.add(fValue1);
        list.add(fValue2);
        list.add(fValue3);
        return list;
    }



}


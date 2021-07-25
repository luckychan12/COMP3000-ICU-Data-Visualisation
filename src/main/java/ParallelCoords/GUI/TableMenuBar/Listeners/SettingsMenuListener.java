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
    private final JFrame messageFrame = new JFrame();

    public void setGeneralFontSize(ActionEvent e1){
        String input= JOptionPane.showInputDialog(messageFrame,"Enter a font size for the menubar text:",
                Integer.toString(UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize()));
        try {
            if(input == null || ("".equals(input)))
            {
                throw new EmptyFieldsException();
            }
            int intInput = Integer.parseInt(input);
            if (intInput < 1 || intInput > 30){
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGeneralSettings().setGeneralFontSize(intInput);
            mainWindow.reloadMenuFonts();
        }
        catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame,"Invalid input value. Please select an Integer value between 1 and 30.", "Error", JOptionPane.WARNING_MESSAGE );
        }
        catch (EmptyFieldsException ignored) { }
    }

    public void setTableHeaderFontSize(ActionEvent e1){
        String input= JOptionPane.showInputDialog(messageFrame,"Enter a font size for the table header text:",
                Integer.toString(UserSettings.getInstance().getUserGeneralSettings().getTableHeaderFontSize()));
        try {
            if(input == null || ("".equals(input)))
            {
                throw new EmptyFieldsException();
            }
            int intInput = Integer.parseInt(input);
            if (intInput < 1 || intInput > 30){
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGeneralSettings().setTableHeaderFontSize(intInput);
            mainWindow.reloadMenuFonts();
        }
        catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame,"Invalid input value. Please select an Integer value between 1 and 30.", "Error", JOptionPane.WARNING_MESSAGE );
        }
        catch (EmptyFieldsException ignored) { }
    }

    public void setTableFontSize(ActionEvent e1){
        String input= JOptionPane.showInputDialog(messageFrame,"Enter a font size for the table text:",
                Integer.toString(UserSettings.getInstance().getUserGeneralSettings().getTableFontSize()));
        try {
            if(input == null || ("".equals(input)))
            {
                throw new EmptyFieldsException();
            }
            int intInput = Integer.parseInt(input);
            if (intInput < 1 || intInput > 30){
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGeneralSettings().setTableFontSize(intInput);
            mainWindow.reloadMenuFonts();
        }
        catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame,"Invalid input value. Please select an Integer value between 1 and 30.", "Error", JOptionPane.WARNING_MESSAGE );
        }
        catch (EmptyFieldsException ignored) { }

    }

    public void setChartHeadersFontSize(ActionEvent e1){
        String input= JOptionPane.showInputDialog(messageFrame,"Enter a font size for the chart header text:",
                Integer.toString(UserSettings.getInstance().getUserGraphSettings().getChartHeaderFontSize()));
        try {
            if(input == null || ("".equals(input)))
            {
                throw new EmptyFieldsException();
            }
            int intInput = Integer.parseInt(input);
            if (intInput < 1 || intInput > 30){
                throw new NumberFormatException();

            }

            UserSettings.getInstance().getUserGraphSettings().setChartHeaderFontSize(intInput);
            mainWindow.repaintCharts(false);
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame,"Invalid input value. Please select an Integer value between 1 and 30.", "Error", JOptionPane.WARNING_MESSAGE );
        }
        catch (EmptyFieldsException ignored) { }
    }

    public void setChartTickFontSize(ActionEvent e1){
        String input = JOptionPane.showInputDialog(messageFrame,"Enter a font size for the chart ticks:",
                Integer.toString(UserSettings.getInstance().getUserGraphSettings().getChartTickFontSize()));

        try {
            if(input == null || ("".equals(input)))
            {
                throw new EmptyFieldsException();
            }
            int intInput = Integer.parseInt(input);
            if (intInput < 1 || intInput > 30){
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGraphSettings().setChartTickFontSize(intInput);
            mainWindow.repaintCharts(false);
        }
        catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame,"Invalid input value. Please select an Integer value between 1 and 15.", "Error", JOptionPane.WARNING_MESSAGE );
        }
        catch (EmptyFieldsException ignored) { }
    }

    public void exit(ActionEvent e) {
        mainWindow.setVisible(false);
        mainWindow.dispose();
        System.exit(0);
    }


}

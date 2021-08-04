package ParallelCoords.GUI.TableMenuBar.Listeners;

import ParallelCoords.GUI.TableMenuBar.ChartColourSettings;
import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.Main;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenuListener {
    private final Main mainWindow;
    private final JFrame messageFrame = new JFrame();

    public SettingsMenuListener(Main mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setGeneralFontSize(ActionEvent e1) {
        String input = JOptionPane.showInputDialog(messageFrame, "Enter a font size for the menubar text:",
                Integer.toString(UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize()));
        try {
            if (input == null || ("".equals(input))) {
                throw new EmptyFieldsException();
            }
            int intInput = Integer.parseInt(input);
            if (intInput < 1 || intInput > 30) {
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGeneralSettings().setGeneralFontSize(intInput);
            mainWindow.reloadMenuFonts();
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame, "Invalid input value. Please select an Integer value between 1 and 30.", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (EmptyFieldsException ignored) {
        }
    }

    public void setTableHeaderFontSize(ActionEvent e1) {
        String input = JOptionPane.showInputDialog(messageFrame, "Enter a font size for the table header text:",
                Integer.toString(UserSettings.getInstance().getUserGeneralSettings().getTableHeaderFontSize()));
        try {
            if (input == null || ("".equals(input))) {
                throw new EmptyFieldsException();
            }
            int intInput = Integer.parseInt(input);
            if (intInput < 1 || intInput > 30) {
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGeneralSettings().setTableHeaderFontSize(intInput);
            mainWindow.reloadMenuFonts();
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame, "Invalid input value. Please select an Integer value between 1 and 30.", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (EmptyFieldsException ignored) {
        }
    }

    public void setTableFontSize(ActionEvent e1) {
        String input = JOptionPane.showInputDialog(messageFrame, "Enter a font size for the table text:",
                Integer.toString(UserSettings.getInstance().getUserGeneralSettings().getTableFontSize()));
        try {
            if (input == null || ("".equals(input))) {
                throw new EmptyFieldsException();
            }
            int intInput = Integer.parseInt(input);
            if (intInput < 1 || intInput > 30) {
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGeneralSettings().setTableFontSize(intInput);
            mainWindow.reloadMenuFonts();
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame, "Invalid input value. Please select an Integer value between 1 and 30.", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (EmptyFieldsException ignored) {
        }

    }

    public void setChartHeadersFontSize(ActionEvent e1) {
        String input = JOptionPane.showInputDialog(messageFrame, "Enter a font size for the chart header text:",
                Integer.toString(UserSettings.getInstance().getUserGraphSettings().getChartHeaderFontSize()));
        try {
            if (input == null || ("".equals(input))) {
                throw new EmptyFieldsException();
            }
            int intInput = Integer.parseInt(input);
            if (intInput < 1 || intInput > 30) {
                throw new NumberFormatException();

            }

            UserSettings.getInstance().getUserGraphSettings().setChartHeaderFontSize(intInput);
            mainWindow.repaintCharts(false);
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame, "Invalid input value. Please select an Integer value between 1 and 30.", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (EmptyFieldsException ignored) {
        }
    }

    public void setChartTickFontSize(ActionEvent e1) {
        String input = JOptionPane.showInputDialog(messageFrame, "Enter a font size for the chart ticks:",
                Integer.toString(UserSettings.getInstance().getUserGraphSettings().getChartTickFontSize()));

        try {
            if (input == null || ("".equals(input))) {
                throw new EmptyFieldsException();
            }
            int intInput = Integer.parseInt(input);
            if (intInput < 1 || intInput > 30) {
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGraphSettings().setChartTickFontSize(intInput);
            mainWindow.repaintCharts(false);
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(messageFrame, "Invalid input value. Please select an Integer value between 1 and 15.", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (EmptyFieldsException ignored) {
        }
    }

    public void toggleChartZoom(ActionEvent e) {
        boolean zoom = UserSettings.getInstance().getUserGraphSettings().getChartZoom();
        UserSettings.getInstance().getUserGraphSettings().setChartZoom(!zoom);
        mainWindow.rescaleCharts(false, true);
    }

    public void exit(ActionEvent e) {
        mainWindow.setVisible(false);
        mainWindow.dispose();
        System.exit(0);
    }

    public void setAxesPerScreenWidth(ActionEvent e, ChartPanel panel) {

        String text2 = "";
        if (panel != null){
            text2 = "\nCurrent dataset has " + panel.getSegments() + " columns";
        }

        String input = JOptionPane.showInputDialog(messageFrame, "Set the number of axes to display per screen width (between 3, 30)" + text2,
                Integer.toString(UserSettings.getInstance().getUserGraphSettings().getAxesPerScreenWidth()));
        try {
            if (input == null || ("".equals(input))) {
                throw new EmptyFieldsException();
            }

            int intInput = Integer.parseInt(input);
            if (intInput < 3 || intInput > 30) {
                throw new NumberFormatException();
            }

            UserSettings.getInstance().getUserGraphSettings().setAxesPerScreenWidth(intInput);
            mainWindow.rescaleCharts(false, false);
        } catch (final NumberFormatException e1) {
            JOptionPane.showMessageDialog(messageFrame, "Invalid input value. Please select an Integer value between 3 and 30.", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (EmptyFieldsException ignored) {
        }
    }

    public ActionListener getChartLineListener(ChartPanel panel){
        String text1 = "Set weights for each colour (Values must be between 0 an 5)";
        String text2 = "Influences the random colour generation (Default = 0) (Increase for more colour)";
        String text3 = "Set Colour Weights";
        int min = 0;
        int max = 5;
        if (panel == null) {
            return e -> {
                ChartColourSettings cs = new ChartColourSettings(text1,text2,text3,min,max,true);
                UserSettings.getInstance().getUserGraphSettings().setChartColourWeights(cs.getFloatValues().get(0),cs.getFloatValues().get(1),cs.getFloatValues().get(2)) ;
            };
        }
        else {
            return e -> {
                ChartColourSettings cs = new ChartColourSettings(text1,text2,text3,min,max,true);
                UserSettings.getInstance().getUserGraphSettings().setChartColourWeights(cs.getFloatValues().get(0),cs.getFloatValues().get(1),cs.getFloatValues().get(2)) ;

                panel.rePrepData(false, false);
            };
        }
    }

    public ActionListener getChartFilterListener(ChartPanel panel){
        String text1 = "Set the colour for the filter sliders (Values must be between 0 and 255)";
        String text2 = "Default = 165,165,165 (grey)";
        String text3 = "Set Colour Values";
        int min = 0;
        int max = 255;
        if (panel == null) {
            return e -> {
                ChartColourSettings cs = new ChartColourSettings(text1,text2,text3,min,max,false);
                UserSettings.getInstance().getUserGraphSettings().setFilterColour(cs.getIntValues().get(0),cs.getIntValues().get(1),cs.getIntValues().get(2));

            };
        }
        else {
            return e -> {
                ChartColourSettings cs = new ChartColourSettings(text1,text2,text3,min,max,false);
                UserSettings.getInstance().getUserGraphSettings().setFilterColour(cs.getIntValues().get(0),cs.getIntValues().get(1),cs.getIntValues().get(2));
                panel.reloadColours();
            };
        }
    }


}

package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartAxisSettingPane;
import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChartSettingsMenu extends JMenu {

    JMenuItem toggleAbsoluteRelative;
    JMenuItem toggleFilterNullAndText;
    JMenuItem setNumTicks;
    JMenuItem reloadChart;
    JMenuItem setMaxMinAxis;
    ChartPanel panel;

    ChartSettingsMenu(ChartPanel panel) {
        super("Chart Controls");
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);
        this.panel = panel;
        toggleAbsoluteRelative = new JMenuItem();
        ActionListener toggleAbsListener = e -> {
            panel.toggleAbsolute();
            setViewMenuName();
        };
        toggleAbsoluteRelative.addActionListener(toggleAbsListener);
        setViewMenuName();

        toggleAbsoluteRelative.setFont(font);
        this.add(toggleAbsoluteRelative);


        ChartSettingListener listener = new ChartSettingListener(panel);

        toggleFilterNullAndText = new JMenuItem();
        ActionListener toggleFilter = e -> {
            listener.toggleFilter();
            setFilterMenuName();
        };
        toggleFilterNullAndText.addActionListener(toggleFilter);
        setFilterMenuName();
        toggleFilterNullAndText.setFont(font);
        this.add(toggleFilterNullAndText);


        setNumTicks = new JMenuItem();
        setNumTicks.addActionListener(e1 -> listener.setTicks());
        setNumTicks.setText("Define number of ticks (Absolute view)");
        setNumTicks.setFont(font);
        this.add(setNumTicks);


        setMaxMinAxis = new JMenuItem();
        ActionListener maxAxis = e -> new ChartAxisSettingPane(panel);
        setMaxMinAxis.addActionListener(maxAxis);
        setMaxMinAxis.setText("Set absolute max axis range");
        setMaxMinAxis.setFont(font);
        this.add(setMaxMinAxis);

        reloadChart = new JMenuItem();
        ActionListener reloadListener = e -> panel.rePrepData(true, true);
        reloadChart.addActionListener(reloadListener);
        reloadChart.setText("Reload Chart");
        reloadChart.setFont(font);
        this.add(reloadChart);

    }

    private void setViewMenuName() {
        if (panel.isAbsolute()) {
            toggleAbsoluteRelative.setText("Toggle Absolute/Relative (Current: Absolute)");
        } else {
            toggleAbsoluteRelative.setText("Toggle Absolute/Relative (Current: Relative)");
        }
    }

    private void setFilterMenuName() {
        if (UserSettings.getInstance().getUserGraphSettings().getChartFilterTextData()) {
            toggleFilterNullAndText.setText("Toggle including null data when filtering (Current: True)");
        } else {
            toggleFilterNullAndText.setText("Toggle including null data when filtering (Current: False)");
        }
    }


    public void reloadFonts() {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);
        toggleAbsoluteRelative.setFont(font);
        setMaxMinAxis.setFont(font);
        reloadChart.setFont(font);
        toggleFilterNullAndText.setFont(font);
        setNumTicks.setFont(font);
    }

}

package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartAxisSettingPane;
import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChartSettingsMenu extends JMenu {
    private final ChartPanel panel;
    private final JMenuItem toggleFilterNullAndText;
    private final JMenuItem toggleExcluded;
    private final JMenuItem toggleAbsoluteRelative;
    private final ArrayList<JComponent> components = new ArrayList<>();

    ChartSettingsMenu(ChartPanel panel) {
        super("Chart Controls");
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);
        this.panel = panel;
        toggleAbsoluteRelative = new JMenuItem();
        components.add(toggleAbsoluteRelative);
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
        components.add(toggleFilterNullAndText);
        ActionListener toggleFilter = e -> {
            listener.toggleFilter();
            setFilterMenuName();
        };
        toggleFilterNullAndText.addActionListener(toggleFilter);
        setFilterMenuName();
        toggleFilterNullAndText.setFont(font);
        this.add(toggleFilterNullAndText);

        toggleExcluded = new JMenuItem();
        components.add(toggleFilterNullAndText);
        ActionListener toggleExcludedListener = e -> {
            listener.toggleExcluded();
            setShowExcludedDataTrails();
        };
        toggleFilterNullAndText.addActionListener(toggleExcludedListener);
        setFilterMenuName();
        toggleFilterNullAndText.setFont(font);
        this.add(toggleFilterNullAndText);



        JMenuItem setNumTicks = new JMenuItem();
        components.add(setNumTicks);
        setNumTicks.addActionListener(e1 -> listener.setTicks());
        setNumTicks.setText("Define number of ticks (Absolute view)");
        setNumTicks.setFont(font);
        this.add(setNumTicks);


        JMenuItem setMaxMinAxis = new JMenuItem();
        components.add(setMaxMinAxis);
        ActionListener maxAxis = e -> new ChartAxisSettingPane(panel);
        setMaxMinAxis.addActionListener(maxAxis);
        setMaxMinAxis.setText("Set absolute max axis range");
        setMaxMinAxis.setFont(font);
        this.add(setMaxMinAxis);

        JMenuItem reloadChart = new JMenuItem();
        components.add(reloadChart);
        ActionListener reloadListener = e -> panel.rePrepData(true, true);
        reloadChart.addActionListener(reloadListener);
        reloadChart.setText("Reload Chart");
        reloadChart.setFont(font);
        this.add(reloadChart);

    }

    private void setViewMenuName() {
        boolean val = panel.isAbsolute();
        toggleAbsoluteRelative.setText("Toggle Absolute/Relative (Current: "+ val +")");
    }

    private void setFilterMenuName() {
        boolean val = UserSettings.getInstance().getUserGraphSettings().getChartFilterTextData();
        toggleFilterNullAndText.setText("Toggle including null data when filtering (Current: "+ val +")");
    }

    private void setShowExcludedDataTrails() {
        boolean val = UserSettings.getInstance().getUserGraphSettings().getChartShowFilterTrails();
        toggleExcluded.setText("Toggle showing trails for excluded data (Current: "+ val +")");
    }


    public void reloadFonts() {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize );
        for (JComponent component: components){
            component.setFont(font);
        }
    }

}

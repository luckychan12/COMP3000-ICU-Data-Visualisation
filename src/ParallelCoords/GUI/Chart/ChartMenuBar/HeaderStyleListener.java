package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.Settings.UserSettings;

public class HeaderStyleListener {
    public void setStaggered(ChartPanel panel){
        UserSettings.getInstance().getUserGraphSettings().setHeaderDisplayType("Staggered");
        panel.changeHeaderStyle();
    }

    public void setTilted(ChartPanel panel){
        UserSettings.getInstance().getUserGraphSettings().setHeaderDisplayType("Tilted");
        panel.changeHeaderStyle();
    }

}

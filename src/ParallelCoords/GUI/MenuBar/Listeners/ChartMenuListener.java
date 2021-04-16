package ParallelCoords.GUI.MenuBar.Listeners;

import ParallelCoords.Data.Data;
import ParallelCoords.GUI.Chart.ChartFrame;
import ParallelCoords.Main;

import java.awt.event.ActionEvent;

public class ChartMenuListener {
    private Main main;
    public ChartMenuListener(Main mainWindow) {this.main = mainWindow;}

    public void generateChart(ActionEvent e){
        Data data = Data.getInstance();
        main.addChart(new ChartFrame(data.getDataStore().get(data.getCurrID())));
    }



}

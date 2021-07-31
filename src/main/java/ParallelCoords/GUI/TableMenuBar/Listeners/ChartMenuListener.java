package ParallelCoords.GUI.TableMenuBar.Listeners;

import ParallelCoords.Data.Data;
import ParallelCoords.GUI.Chart.ChartFrame;
import ParallelCoords.Main;

import java.awt.event.ActionEvent;

public class ChartMenuListener {
    private final Main main;

    public ChartMenuListener(Main mainWindow) {
        this.main = mainWindow;
    }

    public void generateChart(ActionEvent e) {
        Data data = Data.getInstance();
        try {
            main.addChart(new ChartFrame(data.getDataStore().get(data.getCurrID()), main));
        } catch (IndexOutOfBoundsException e2) {
            e2.printStackTrace();
            System.out.println("No data loaded");
        }

    }


}

package ParallelCoords.GUI.Chart;

import ParallelCoords.Chart.ParallelCoordinatesModel;
import ParallelCoords.Data.Data;
import ParallelCoords.Data.DataTable;
import ParallelCoords.Exceptions.NoDataException;
import ParallelCoords.Main;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ChartFrame extends JFrame implements ComponentListener {
    private Main mainWindow;
    private ParallelCoordinatesModel chart;
    private ParallelCoordinatesChart chartPanel;
    private DataTable dataTable;


    public ChartFrame(Main mainWindow,ParallelCoordinatesModel chart) throws NoDataException {
        super(chart.getTitle());
        this.mainWindow = mainWindow;
        dataTable = Data.getInstance().getDataStore().get(Data.getInstance().getCurrID());
        if (dataTable.getNumberOfColumns() < 1) {
            throw new NoDataException();
        }

    }


    @Override
    public void componentResized(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}

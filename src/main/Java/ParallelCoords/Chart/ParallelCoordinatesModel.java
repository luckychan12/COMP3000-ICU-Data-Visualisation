package ParallelCoords.Chart;

import ParallelCoords.Data.Data;
import ParallelCoords.Data.DataTable;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ParallelCoordinatesModel {
    private Point location;
    private Dimension frameSize;
    private DataTable dataTable;
    private boolean antiAliasing;
    private boolean useAlpha;

    private List<Axis> axes = new LinkedList<>();

    public ParallelCoordinatesModel(int tableID){
        this.dataTable = Data.getInstance().getDataStore().get(Data.getInstance().getCurrID());
        this.frameSize = new Dimension(800, 600);
        this.location = new Point(100, 100);
        this.antiAliasing = true;
        this.useAlpha = true;

        dataTable.sortColumnsByPosition();
        for (int i = 0; i < dataTable.getNumberOfColumns(); i++) {
            Axis newAxis = new Axis(dataTable, this, dataTable.getColumn(i).getColumnID());
            axes.add(newAxis);
        }


    }

    public String getTitle() {
        return "Parallel Coordinates Chart";
    }

    public Dimension getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(Dimension size) {
        this.frameSize = size;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }


    public boolean isAntiAliasing() {
        return antiAliasing;
    }

    public void setAntiAliasing(boolean antiAliasing) {
        this.antiAliasing = antiAliasing;
    }

    public boolean isUseAlpha() {
        return useAlpha;
    }

    public void setUseAlpha(boolean useAlpha) {
        this.useAlpha = useAlpha;
    }
}

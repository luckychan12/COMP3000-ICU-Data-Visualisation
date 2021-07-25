package ParallelCoords.GUI.Chart;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FullLineData {
    private final ArrayList<PartialLineData> lineData = new ArrayList<>();
    private final HashMap<Integer, Boolean> filteredData = new HashMap<>();
    private boolean showData = true;
    private Color color;

    FullLineData(){}

    public void addData(PartialLineData dat){
        lineData.add(dat);
    }

    public void setShowData(int segment, boolean value){
        filteredData.put(segment, value);
    }

    public Boolean showDataSegment(int segment){
        return filteredData.get(segment);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Boolean filterData(){
        if (!showData){
            return false;
        }
        Iterator<Map.Entry<Integer, Boolean>> it = filteredData.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Boolean> pair = it.next();
            if (!pair.getValue()){
                return false;
            }
            it.remove();
        }
        return true;
    }


    public void setShowData(boolean showData) {
        this.showData = showData;
    }

    public boolean getShowData() {
        return showData;
    }

    public ArrayList<PartialLineData> getData(){
        return lineData;
    }

}

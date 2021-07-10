package ParallelCoords.GUI.Chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FullLineData {
    private ArrayList<PartialLineData> lineData = new ArrayList<>();
    private HashMap<Integer, Boolean> showData = new HashMap<>();

    FullLineData(){}

    public void addData(PartialLineData dat){
        lineData.add(dat);
    }

    public void setShowData(int segment, boolean value){
        showData.put(segment, value);
    }

    public Boolean showDataSegment(int segment){
        return showData.get(segment);
    }

    public Boolean showData(){
        Iterator<Map.Entry<Integer, Boolean>> it = showData.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Boolean> pair = it.next();
            if (!pair.getValue()){
                return false;
            }
            it.remove();
        }
        return true;
    }

    public ArrayList<PartialLineData> getData(){
        return lineData;
    }

}

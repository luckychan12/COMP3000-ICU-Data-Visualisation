package ParallelCoords.GUI.Chart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DataDisplay extends JComponent {
    private ArrayList<FullLineData> fullLineData = new ArrayList<>();
    DataDisplay(Dimension dimension){
    setVisible(true);
    setBounds(0,0, dimension.width, dimension.height);
    }
    public void addLineData(FullLineData data){
        fullLineData.add(new FullLineData());
    }

    public ArrayList<FullLineData> getFullLineData() {
        return fullLineData;
    }

    public void redrawData(){
        this.repaint();
    }

    public void clearData(){
        fullLineData.clear();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (FullLineData data: fullLineData) {
            if (data.showData()){
                for (PartialLineData line:data.getData()) {
                    g2.setColor(line.getColour());
                    g2.setStroke(line.getLineStroke());
                    g2.drawLine(line.getPoint1().x, line.getPoint1().y, line.getPoint2().x,line.getPoint2().y);
                }
            }

        }
    }
}

package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartPanel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class HeaderStyleMenu  extends JMenu {
    public HeaderStyleMenu(ChartPanel panel){
        super("Header Style");

        HeaderStyleListener listener = new HeaderStyleListener();
        JMenuItem setStaggered = new JMenuItem();
        setStaggered.setText("Staggered");
        ActionListener staggeredListener = e -> listener.setStaggered(panel);
        setStaggered.addActionListener(staggeredListener);
        this.add(setStaggered);

        JMenuItem setTilted = new JMenuItem();
        setTilted.setText("Tilted");
        ActionListener tiltedListener = e -> listener.setTilted(panel);
        setTilted.addActionListener(tiltedListener);
        this.add(setTilted);
    }

}

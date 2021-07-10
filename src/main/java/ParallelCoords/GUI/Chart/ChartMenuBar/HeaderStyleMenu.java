package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartPanel;

import javax.swing.*;

public class HeaderStyleMenu  extends JMenu {
    public HeaderStyleMenu(ChartPanel panel){
        super("Header Style");

        HeaderStyleListener listener = new HeaderStyleListener();
        JMenuItem setStaggered = new JMenuItem();
        setStaggered.setText("Staggered View");
        setStaggered.addActionListener(e -> listener.setStaggered(panel));
        this.add(setStaggered);

        JMenuItem setTilted = new JMenuItem();
        setTilted.setText("Tilted View");
        setTilted.addActionListener(e -> listener.setTilted(panel));
        this.add(setTilted);

        JMenuItem setAngle = new JMenuItem();
        setAngle.setText("Set Tilt Angle");
        setAngle.addActionListener(e -> listener.setAngle(panel));


        JMenuItem setTiltedPadding = new JMenuItem();
        setTiltedPadding.setText("Set top padding percentage");
        setTiltedPadding.addActionListener(e -> listener.setTiltedPadding(panel));


        JMenu tiltedSettingMenu = new JMenu();
        tiltedSettingMenu.setText("Tilted mode settings");
        tiltedSettingMenu.add(setAngle);
        tiltedSettingMenu.add(setTiltedPadding);

        this.add(tiltedSettingMenu);

    }

}

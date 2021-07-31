package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HeaderStyleMenu extends JMenu {
    private ArrayList<JComponent> components = new ArrayList<>();

    public HeaderStyleMenu(ChartPanel panel) {
        super("Header Style");
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);

        HeaderStyleListener listener = new HeaderStyleListener();
        JMenuItem setStaggered = new JMenuItem();
        components.add(setStaggered);
        setStaggered.setText("Staggered View");
        setStaggered.setFont(font);
        setStaggered.addActionListener(e -> listener.setStaggered(panel));
        this.add(setStaggered);

        JMenuItem setTilted = new JMenuItem();
        components.add(setTilted);
        setTilted.setText("Tilted View");
        setTilted.setFont(font);
        setTilted.addActionListener(e -> listener.setTilted(panel));
        this.add(setTilted);

        JMenuItem setAngle = new JMenuItem();
        components.add(setAngle);
        setAngle.setText("Set Tilt Angle");
        setAngle.setFont(font);
        setAngle.addActionListener(e -> listener.setAngle());


        JMenuItem setTiltedPadding = new JMenuItem();
        components.add(setTiltedPadding);
        setTiltedPadding.setText("Set top padding percentage");
        setTiltedPadding.setFont(font);
        setTiltedPadding.addActionListener(e -> listener.setTiltedPadding(panel));


        JMenu tiltedSettingMenu = new JMenu();
        components.add(tiltedSettingMenu);
        tiltedSettingMenu.setText("Tilted mode settings");
        tiltedSettingMenu.setFont(font);
        tiltedSettingMenu.add(setAngle);
        tiltedSettingMenu.add(setTiltedPadding);

        this.add(tiltedSettingMenu);

    }


    public void reloadFonts() {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize );
        for (JComponent component: components){
            component.setFont(font);
        }
    }
}

package ParallelCoords.GUI.Chart.ChartMenuBar;

import ParallelCoords.GUI.Chart.ChartPanel;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;

public class HeaderStyleMenu  extends JMenu {
    HeaderStyleListener listener;
    JMenuItem setTilted;
    JMenuItem setAngle;
    JMenuItem setTiltedPadding;
    JMenu tiltedSettingMenu;
    JMenuItem setStaggered;
    public HeaderStyleMenu(ChartPanel panel) {
        super("Header Style");
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);

        listener = new HeaderStyleListener();
        setStaggered= new JMenuItem();
        setStaggered.setText("Staggered View");
        setStaggered.setFont(font);
        setStaggered.addActionListener(e -> listener.setStaggered(panel));
        this.add(setStaggered);

        setTilted = new JMenuItem();
        setTilted.setText("Tilted View");
        setTilted.setFont(font);
        setTilted.addActionListener(e -> listener.setTilted(panel));
        this.add(setTilted);

        setAngle = new JMenuItem();
        setAngle.setText("Set Tilt Angle");
        setAngle.setFont(font);
        setAngle.addActionListener(e -> listener.setAngle());


        setTiltedPadding = new JMenuItem();
        setTiltedPadding.setText("Set top padding percentage");
        setTiltedPadding.setFont(font);
        setTiltedPadding.addActionListener(e -> listener.setTiltedPadding(panel));


        tiltedSettingMenu = new JMenu();
        tiltedSettingMenu.setText("Tilted mode settings");
        tiltedSettingMenu.setFont(font);
        tiltedSettingMenu.add(setAngle);
        tiltedSettingMenu.add(setTiltedPadding);

        this.add(tiltedSettingMenu);

    }


    public void reloadFonts() {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);
        setTilted.setFont(font);
        setAngle.setFont(font);
        setTiltedPadding.setFont(font);
        tiltedSettingMenu.setFont(font);
        setStaggered.setFont(font);
    }
}

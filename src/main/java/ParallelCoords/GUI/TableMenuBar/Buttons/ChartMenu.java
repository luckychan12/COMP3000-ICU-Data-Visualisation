package ParallelCoords.GUI.TableMenuBar.Buttons;

import ParallelCoords.GUI.TableMenuBar.Listeners.ChartMenuListener;
import ParallelCoords.Main;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;

public class ChartMenu extends JMenu {
    JMenuItem mi1;
    public ChartMenu(Main mainWindow) {
        super("Chart");
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize );

        ChartMenuListener listener;
        listener = new ChartMenuListener(mainWindow);
        mi1 = new JMenuItem("Generate Chart");
        mi1.addActionListener(listener::generateChart);
        mi1.setFont(font);
        this.add(mi1);

    }

    public void reloadFonts() {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);
        mi1.setFont(font);

    }
}

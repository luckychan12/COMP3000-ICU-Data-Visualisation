package ParallelCoords.GUI.TableMenuBar.Buttons;

import ParallelCoords.GUI.TableMenuBar.Listeners.DataMenuListener;
import ParallelCoords.Main;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;

public class DataImportMenu extends JMenu {
    JMenuItem mi1,mi2,mi3;
    public DataImportMenu(Main mainWindow){
        super("Load Data");
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize );


        DataMenuListener listener = new DataMenuListener(mainWindow);
        mi1 = new JMenuItem("Load Data with Headers");
        mi1.addActionListener(listener::importDataHeaders);
        mi1.setFont(font);
        mi2 = new JMenuItem("Load Data without Headers");
        mi2.addActionListener(listener::importDataNoHeaders);
        mi2.setFont(font);
        mi3 = new JMenuItem("Set Delimiter");
        mi3.addActionListener(listener::setDelimiter);
        mi3.setFont(font);

        this.add(mi1);
        this.add(mi2);
        this.add(mi3);



    }

    public void reloadFonts() {
        int fontSize = UserSettings.getInstance().getUserGeneralSettings().getGeneralFontSize();
        Font font = new Font("Calibri", Font.BOLD, fontSize);
        mi1.setFont(font);
        mi2.setFont(font);
        mi3.setFont(font);
    }
}

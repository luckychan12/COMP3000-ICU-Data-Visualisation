package ParallelCoords.GUI.MenuBar;

import ParallelCoords.Main;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
    /**
     *  
     */
    private static final long serialVersionUID = 8826487342594709581L;

    private final FileMenu fileMenu;
    private final DataImportMenu dataImportMenu;
    private final ChartMenu chartMenu;


    public MenuBar(Main mainWindow) {
        this.fileMenu = new FileMenu(mainWindow);
        this.add(fileMenu);
        this.dataImportMenu = new DataImportMenu(mainWindow);
        this.add(dataImportMenu);
        this.chartMenu = new ChartMenu(mainWindow);
        this.add(chartMenu);
    }
}
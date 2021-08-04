package ParallelCoords.GUI.Chart;

import ParallelCoords.Data.DataColumn;
import ParallelCoords.Data.DataTable;
import ParallelCoords.GUI.Chart.ChartMenuBar.ColumnDataMenu;
import ParallelCoords.GUI.Chart.Filter.Axis;
import ParallelCoords.GUI.Chart.Filter.FilterPair;
import ParallelCoords.Settings.UserGraphSettings;
import ParallelCoords.Settings.UserSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class ChartPanel extends JPanel {
    private final Dimension screenSize;
    private final ChartFrame frame;
    private boolean absolute;
    private int height, width;
    private final int nullPadding = 50;
    private final float percentageWidth = 0.9f;
    private float percentageHeight = 0.85f;
    private final int shiftDown = 40;
    private boolean forceTilted;

    private final DataTable dataTable;
    private DataTable dataTableMask;
    private int segments;
    private int segmentSize;
    private final Point startPoint = new Point();
    private int axisLength;
    private final ArrayList<ColumnDataMenu> menus = new ArrayList<>();
    private ArrayList<FilterPair> filterSliders = new ArrayList<>();
    private final ArrayList<Axis> allAxes = new ArrayList<>();
    private DataDisplay dataDisplay;

    UserGraphSettings graphSettings = UserSettings.getInstance().getUserGraphSettings();


    ChartPanel(ChartFrame frame, Dimension screenSize, DataTable dataTable) {
        this.frame = frame;
        this.screenSize = screenSize;
        this.setBackground(Color.white);
        this.setPreferredSize(screenSize);
        this.setAutoscrolls(true);
        this.dataTable = dataTable;
        dataTableMask = new DataTable(dataTable.getNumRows(), dataTable.hasDefinedHeaders(), dataTable.getData());

        this.absolute = graphSettings.getChartAbsolute();
        generateDataMenus();
        dataDisplay = new DataDisplay(dataTableMask, this);
        calculateInitialPositionValues();

        if (dataTableMask.getNumberOfColumns() < graphSettings.getAxesPerScreenWidth()){
            graphSettings.setAxesPerScreenWidth(dataTableMask.getNumberOfColumns());
        }


        this.add(dataDisplay);
        for (int i = 0; i < dataTableMask.getMaxSize(); i++) {
            addFullLineData();
        }

        rePrepData(true, true);

        createFilterSliders();
    }

    public DataTable getDataTableMask() {
        return dataTableMask;
    }

    public void setDataTableMask(DataTable dataTableMask) {
        this.dataTableMask = dataTableMask;
    }

    public void createFilterSliders() {
        checkHiddenColumns();
        ArrayList<FilterPair> newFilterSliders = new ArrayList<>();
        for (int i = 0; i < segments; i++) {
            FilterPair newSlider = new FilterPair(startPoint.y, startPoint.y + axisLength, startPoint.x + i * segmentSize, this, i);
            newFilterSliders.add(newSlider);
        }

        for (FilterPair slider : filterSliders) {
            slider.removeSlider();
        }
        filterSliders = newFilterSliders;

    }

    public void repositionSliders() {
        for (int i = 0; i < segments; i++) {
            filterSliders.get(i).setLowerX(startPoint.x + i * segmentSize);
            filterSliders.get(i).setUpperX(startPoint.x + i * segmentSize);
        }
    }

    public ArrayList<ColumnDataMenu> getDataMenus() {
        return menus;
    }

    private void addFullLineData() {
        FullLineData lineData = new FullLineData();
        lineData.setColor(dataDisplay.genColour());
        dataDisplay.addLineData(lineData);
    }

    public ChartFrame getFrame() {
        return frame;
    }

    public void toggleAbsolute() {
        absolute = !absolute;
        graphSettings.setChartAbsolute(absolute);
        this.rePrepData(false, false);
    }

    public void changeHeaderStyle() {
        calculateInitialPositionValues();
        for (FilterPair slider : filterSliders) {
            slider.removeSlider();
        }

        for (int i = 0; i < segments; i++) {
            filterSliders.add(new FilterPair(startPoint.y, startPoint.y + axisLength, startPoint.x + i * segmentSize, this, i));
        }


        rePrepData(false, false);
    }

    public ArrayList<FilterPair> getFilterSliders() {
        return filterSliders;
    }

    public void checkHiddenColumns() {
        int i = 0;
        ArrayList<DataColumn> columns = new ArrayList<>();
        for (DataColumn col : dataTable.getData()) {
            if (!menus.get(i).isHidden()) {
                columns.add(col);
            }
            i++;
        }
        dataTableMask.setColumns(columns);
        dataDisplay.setDataTable(dataTableMask);
        segments = dataTableMask.getNumberOfColumns();
    }

    public void calculateInitialPositionValues() {
        forceTilted = false;
        for (DataColumn column : dataTableMask.getData()) {
            if (column.getColumnName().length() > 24) {
                forceTilted = true;
                break;
            }
        }

        if (graphSettings.getChartZoom()) {
            height = (int) (screenSize.height * 0.70f);

        } else {
            height = (int) (screenSize.height * 0.90f);
        }
        checkHiddenColumns();

        width = screenSize.width;
        int increment = 0;
        percentageHeight = 0.83f;
        if (graphSettings.getHeaderDisplayType().equals("Tilted") || forceTilted) {
            increment = 2;
            percentageHeight = 1f - (graphSettings.getTiltedTopPadding() * 0.01f);
        }

        segments = dataTableMask.getNumberOfColumns();
        int tmp = segments;
        segments = graphSettings.getAxesPerScreenWidth();
        //if (segments > graphSettings.getAxesPerScreenWidth()) {
        //    segments = graphSettings.getAxesPerScreenWidth();
        //}
        //else if(segments < graphSettings.getAxesPerScreenWidth()){
        //    segments = segments + 2;
        //}


        segmentSize = (int) (width * percentageWidth) / segments;
        startPoint.x = width / 2 - ((segmentSize / 2) * (segments - 1));
        startPoint.y = (int) (height - height * percentageHeight) + shiftDown;
        segments = tmp;
        //percentage height is squared to make percentage axis length relative to percentage height and not the screen height

        axisLength = (int) (percentageHeight * percentageHeight * height);
        this.setPreferredSize(new Dimension((segmentSize * (dataTableMask.getNumberOfColumns() + increment) + startPoint.x - 20), height));
        dataDisplay.setDrawSize(this.getPreferredSize());
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);


    }

    public void resetFilters() {
        for (FilterPair filter : filterSliders) {
            filter.resetPos();
        }
    }


    public DataDisplay getDataDisplay() {
        return dataDisplay;
    }

    public void rePrepData(Boolean resetFilters, boolean showWarning) {
        resetData();
        checkHiddenColumns();
        if (resetFilters) {
            resetFilters();
        }
        dataDisplay.prepData(startPoint, axisLength, segments, segmentSize, showWarning);
        updateFilters();

    }

    private void resetData() {
        dataDisplay.clearData();
        for (int i = 0; i < dataTableMask.getMaxSize(); i++) {
            addFullLineData();
        }
    }

    private void updateFilters() {
        for (FilterPair filter : filterSliders) {
            filter.updateValues();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        this.setBackground(Color.white);
        g2.clearRect(0, 0, width, height);
        drawAxis();
        dataDisplay.repaint();
        drawHeaders(g2, startPoint, segments, segmentSize);

        setVisible(true);
    }

    private void drawAxis() {
        checkHiddenColumns();
        for (Axis axis : allAxes) {
            this.remove(axis);
        }

        for (int i = 0; i < segments; i++) {
            Axis newAxis;
            if (i == 0 || i == segments - 1) {
                newAxis = new Axis(i, this.getBounds(), true, this);
            } else {
                newAxis = new Axis(i, this.getBounds(), false, this);
            }
            allAxes.add(newAxis);
            this.add(newAxis);
        }
    }


    private FontMetrics generateFontMetrics(Graphics2D g2, Font font) {
        g2.setFont(font);
        g2.setColor(Color.BLACK);

        return g2.getFontMetrics(font);
    }


    private void drawHeaders(Graphics2D g2, Point startPoint, int segments, int segmentSize) {
        String displayType = graphSettings.getHeaderDisplayType();
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, UserSettings.getInstance()
                .getUserGraphSettings().getChartHeaderFontSize());
        FontMetrics metrics = generateFontMetrics(g2, font);
        AffineTransform affineTransform = new AffineTransform();
        AffineTransform orig = g2.getTransform();
        affineTransform.rotate(Math.toRadians(0), 0, 0);


        if (displayType.equals("Tilted") || forceTilted) {
            affineTransform.rotate(Math.toRadians(-graphSettings.getTiltedAngle()), 0, 0);
        }
        Font rotatedFont = metrics.getFont().deriveFont(affineTransform);
        g2.setFont(rotatedFont);
        for (int j = 0; j < segments; j++) {
            int spacing = 80;
            int height;
            int tiltedHeight = 55;
            int height2 = spacing + metrics.getHeight();

            int width = metrics.stringWidth(dataTableMask.getColumn(j).getColumnName());




            if (displayType.equals("Tilted") || forceTilted) {
                height = tiltedHeight;
                g2.drawString(dataTableMask.getColumn(j).getColumnName(), startPoint.x + segmentSize * j,
                        (startPoint.y - height));
            }
            else {
                height = spacing;
                if (j % 2 == 0) {
                    height = height2;
                }
                g2.drawString(dataTableMask.getColumn(j).getColumnName(), startPoint.x + segmentSize * j - width / 2,
                        (startPoint.y - height));
            }


        }
        g2.setTransform(orig);
    }

    public void generateDataMenus() {
        for (DataColumn column : dataTableMask.getData()) {
            ColumnDataMenu newMenu = new ColumnDataMenu(column, this);
            getDataMenus().add(newMenu);
            //newMenu.setColumn(column.getColumnID());
            newMenu.setText(column.getColumnName());

            JMenuItem editName = new JMenuItem();
            editName.addActionListener(e -> newMenu.editName());
            newMenu.add(editName);
            newMenu.getItems().add(editName);
            editName.setText("Edit column name");

            JMenuItem invertData = new JMenuItem();
            invertData.addActionListener(e -> newMenu.invert());
            newMenu.add(invertData);
            newMenu.getItems().add(invertData);
            invertData.setText("Invert Axis");

            JMenuItem hideData = new JMenuItem();
            hideData.addActionListener(e -> newMenu.toggleHidden());
            newMenu.add(hideData);
            hideData.setText("Hide data column");
            newMenu.getItems().add(hideData);
        }
    }


    public boolean isAbsolute() {
        return absolute;
    }

    public BasicStroke getDotDashStroke() {
        return new BasicStroke(graphSettings.getChartLineThickness() + 1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL,
                1f, new float[]{10f, 20f, 3f, 20f}, 0f);
    }

    public BasicStroke getDashedLine() {
        return new BasicStroke(graphSettings.getChartLineThickness() + 1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{9}, 0);
    }

    public BasicStroke getLineStroke() {
        return new BasicStroke(graphSettings.getChartLineThickness());
    }


    public int getSegmentSize() {
        return segmentSize;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public int getAxisLength() {
        return axisLength;
    }

    public int getNullPadding() {
        return nullPadding;
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    public void reloadColours() {
        for (FilterPair slider : filterSliders) {
            slider.getUpperSlider().repaint();
        }
    }

    public int getSegments() {
        return segments;
    }
}

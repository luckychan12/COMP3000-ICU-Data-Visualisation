package ParallelCoords.GUI.Chart;

import java.awt.*;

public class PartialLineData {
    private int segmentStart;
    private int getSegmentEnd;
    private Point point1;
    private Point point2;
    private Color colour;
    private BasicStroke lineStroke;
    private Double percentage1;
    private Double percentage2;


    public PartialLineData(Point point1, Point point2, Color colour,
                           BasicStroke lineStroke, int segmentStart, int getSegmentEnd, Double percentage1, Double percentage2) {
        this.point1 = point1;
        this.point2 = point2;
        this.colour = colour;
        this.lineStroke = lineStroke;
        this.segmentStart = segmentStart;
        this.getSegmentEnd = getSegmentEnd;
        this.percentage1 = percentage1;
        this.percentage2 = percentage2;
    }

    public Color getColour() {
        return colour;
    }

    public Double getPercentage1() {
        return percentage1;
    }

    public void setPercentage1(Double percentage1) {
        this.percentage1 = percentage1;
    }

    public Double getPercentage2() {
        return percentage2;
    }

    public void setPercentage2(Double percentage2) {
        this.percentage2 = percentage2;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public Stroke getLineStroke() {
        return lineStroke;
    }

    public void setLineStroke(BasicStroke lineStroke) {
        this.lineStroke = lineStroke;
    }


    public int getSegmentStart() {
        return segmentStart;
    }

    public void setSegmentStart(int segmentStart) {
        this.segmentStart = segmentStart;
    }

    public int getGetSegmentEnd() {
        return getSegmentEnd;
    }

    public void setGetSegmentEnd(int getSegmentEnd) {
        this.getSegmentEnd = getSegmentEnd;
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }
}

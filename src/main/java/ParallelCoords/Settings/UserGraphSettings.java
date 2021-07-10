package ParallelCoords.Settings;

import javax.swing.*;

public class UserGraphSettings {
    public void setHeaderDisplayType(String headerType) {
        UserSettings.getInstance().updateProperties("HeaderDisplayType", headerType);
    }

    public String getHeaderDisplayType(){
        String defaultVal = "Staggered";
        return UserSettings.getInstance().getPropValue("HeaderDisplayType", defaultVal);
    }

    public void setTiltedAngle(int angle){
        UserSettings.getInstance().updateProperties("TiltAngle", Integer.toString(angle));
    }

    public int getTiltedAngle(){
        String defaultVal = "15";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("TiltAngle", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nsaved tilt angle from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setTiltedAngle(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setTiltedTopPaddingPercentage(int padding){
        UserSettings.getInstance().updateProperties("TiltedTopPaddingPercentage", Integer.toString(padding));
    }

    public int getTiltedTopPadding(){
        String defaultVal = "20";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("TiltedTopPaddingPercentage", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nsaved tilted view padding from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setTiltedTopPaddingPercentage(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setGraphHeaderFontSize(int value){
        UserSettings.getInstance().updateProperties("GraphHeaderFontSize", Integer.toString(value));
    }

    public int getGraphHeaderFontSize(){
        String defaultVal = "14";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("GraphHeaderFontSize", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nheader font size from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setGraphHeaderFontSize(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setGraphLineThickness(float value){
        UserSettings.getInstance().updateProperties("GraphLineThickness", Float.toString(value));
    }

    public float getGraphLineThickness(){
        String defaultVal = "1.5";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("GraphLineThickness", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \ngraph line thickness from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setGraphLineThickness(Float.parseFloat(defaultVal));
            return Float.parseFloat(defaultVal);
        }
    }

    public void setGraphDashedLineThickness(float value){
        UserSettings.getInstance().updateProperties("GraphDashedLineThickness", Float.toString(value));
    }

    public float getGraphDashedLineThickness(){
        String defaultVal = "2.5";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("GraphDashedLineThickness", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \ngraph line thickness from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setGraphDashedLineThickness(Float.parseFloat(defaultVal));
            return Float.parseFloat(defaultVal);
        }
    }

    public void setGraphDotDashedLineThickness(float value){
        UserSettings.getInstance().updateProperties("GraphDotDashedLineThickness", Float.toString(value));
    }

    public float getGraphDotDashedLineThickness(){
        String defaultVal = "2.2";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("GraphDotDashedLineThickness", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \ngraph line thickness from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setGraphDotDashedLineThickness(Float.parseFloat(defaultVal));
            return Float.parseFloat(defaultVal);
        }
    }


    public void setGraphRelativeValueLow(int value){
        UserSettings.getInstance().updateProperties("GraphRelativeValueLow", Integer.toString(value));
    }

    public int getGraphRelativeValue(){
        String defaultVal = "0";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("GraphRelativeValueLow", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nsaved relative value from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setGraphRelativeValueLow(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setGraphRelativeValueHigh(int value){
        UserSettings.getInstance().updateProperties("GraphRelativeValueLow", Integer.toString(value));
    }

    public int getGraphRelativeValueHigh(){
        String defaultVal = "5";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("GraphRelativeValueHigh", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nsaved relative value from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setGraphRelativeValueHigh(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setNumberOfTicks(int value){
        UserSettings.getInstance().updateProperties("GraphRelativeValueLow", Integer.toString(value));
    }

    public int getNumberOfTicks(){
        String defaultVal = "5";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("NumberOfTicks", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \ngraph settings from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setNumberOfTicks(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }



}

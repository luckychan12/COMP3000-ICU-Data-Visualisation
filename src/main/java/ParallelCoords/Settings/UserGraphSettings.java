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

    public void setChartHeaderFontSize(int value){
        UserSettings.getInstance().updateProperties("ChartHeaderFontSize", Integer.toString(value));
    }

    public int getChartHeaderFontSize(){
        String defaultVal = "16";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("ChartHeaderFontSize", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nfont size from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setChartHeaderFontSize(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setChartTickFontSize(int value){
        UserSettings.getInstance().updateProperties("ChartTickFontSize", Integer.toString(value));
    }

    public int getChartTickFontSize(){
        String defaultVal = "14";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("ChartTickFontSize", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nfont size from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setChartTickFontSize(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setChartRedWeight(float value){
        UserSettings.getInstance().updateProperties("ChartRedWeight", Float.toString(value));
    }

    public float getChartRedWeight(){
        String defaultVal = "0";
        try {
            return Float.parseFloat(UserSettings.getInstance().getPropValue("ChartRedWeight", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading saved colour weights from storage", "Error", JOptionPane.WARNING_MESSAGE);
            setChartRedWeight(Float.parseFloat(defaultVal));
            return Float.parseFloat(defaultVal);
        }
    }
    public void setChartGreenWeight(float value){
        UserSettings.getInstance().updateProperties("ChartGreenWeight", Float.toString(value));
    }

    public float getChartGreenWeight(){
        String defaultVal = "0";
        try {
            return Float.parseFloat(UserSettings.getInstance().getPropValue("ChartGreenWeight", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading saved colour weights from storage", "Error", JOptionPane.WARNING_MESSAGE);
            setChartGreenWeight(Float.parseFloat(defaultVal));
            return Float.parseFloat(defaultVal);
        }
    }

    public void setChartBlueWeight(float value){
        UserSettings.getInstance().updateProperties("ChartBlueWeight", Float.toString(value));
    }

    public float getChartBlueWeight(){
        String defaultVal = "0";
        try {
            return Float.parseFloat(UserSettings.getInstance().getPropValue("ChartBlueWeight", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading saved colour weights from storage", "Error", JOptionPane.WARNING_MESSAGE);
            setChartBlueWeight(Float.parseFloat(defaultVal));
            return Float.parseFloat(defaultVal);
        }
    }

    public void setChartNumTicks(int value){
        UserSettings.getInstance().updateProperties("ChartNumTicks", Integer.toString(value));
    }

    public int getChartNumTicks(){
        String defaultVal = "5";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("ChartNumTicks", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \n number of ticks to display on graph", "Error", JOptionPane.WARNING_MESSAGE);
            setChartNumTicks(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setChartAxisMax(double value){
        UserSettings.getInstance().updateProperties("ChartAxisMax", Double.toString(value));
    }

    public double getChartAxisMax(){
        String defaultVal = "5";
        try {
            return Double.parseDouble(UserSettings.getInstance().getPropValue("ChartAxisMax", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \n chart axis settings", "Error", JOptionPane.WARNING_MESSAGE);
            setChartAxisMax(Double.parseDouble(defaultVal));
            return Double.parseDouble(defaultVal);
        }
    }

    public void setChartAxisMin(double value){
        UserSettings.getInstance().updateProperties("ChartAxisMin", Double.toString(value));
    }

    public double getChartAxisMin(){
        String defaultVal = "1.5";
        try {
            return Double.parseDouble(UserSettings.getInstance().getPropValue("ChartAxisMin", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \n chart axis settings", "Error", JOptionPane.WARNING_MESSAGE);
            setChartAxisMin(Double.parseDouble(defaultVal));
            return Double.parseDouble(defaultVal);
        }

    }

    public void setChartZoom(boolean value){
        UserSettings.getInstance().updateProperties("ChartZoom", Boolean.toString(value));
    }

    public boolean getChartZoom(){
        String defaultVal = "false";
        try {
            return Boolean.parseBoolean(UserSettings.getInstance().getPropValue("ChartZoom", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            setChartZoom(false);
            return false;
        }
    }

    public void setChartAbsolute(boolean value){
        UserSettings.getInstance().updateProperties("ChartAbsolute", Boolean.toString(value));
    }

    public boolean getChartAbsolute(){
        String defaultVal = "false";
        try {
            return Boolean.parseBoolean(UserSettings.getInstance().getPropValue("ChartAbsolute", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            setChartAbsolute(false);
            return false;
        }
    }

    public void setChartFilterTextData(boolean value){
        UserSettings.getInstance().updateProperties("ChartFilterTextData", Boolean.toString(value));
    }

    public boolean getChartFilterTextData(){
        String defaultVal = "false";
        try {
            return Boolean.parseBoolean(UserSettings.getInstance().getPropValue("ChartFilterTextData", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            setChartFilterTextData(false);
            return false;
        }
    }

    public void setAxesPerScreenWidth(int value){
        UserSettings.getInstance().updateProperties("AxesPerScreenWidth", Integer.toString(value));
    }

    public int getAxesPerScreenWidth(){
        String defaultVal = "14";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("AxesPerScreenWidth", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            setAxesPerScreenWidth(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setAxesThickness(float value){
        UserSettings.getInstance().updateProperties("AxesThickness", Float.toString(value));
    }

    public float getAxesThickness(){
        String defaultVal = "2";
        try {
            return Float.parseFloat(UserSettings.getInstance().getPropValue("AxesThickness", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            setAxesThickness(Float.parseFloat(defaultVal));
            return Float.parseFloat(defaultVal);
        }
    }


    public void setChartLineThickness(float value){
        UserSettings.getInstance().updateProperties("ChartLineThickness", Float.toString(value));
    }

    public float getChartLineThickness(){
        String defaultVal = "1.5";
        try {
            return Float.parseFloat(UserSettings.getInstance().getPropValue("ChartLineThickness", defaultVal));
        }
        catch (NumberFormatException e){
            JFrame frame = new JFrame();
            setChartLineThickness(Float.parseFloat(defaultVal));
            return Float.parseFloat(defaultVal);
        }
    }



}

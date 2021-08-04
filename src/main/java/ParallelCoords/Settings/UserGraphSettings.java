package ParallelCoords.Settings;

import javax.swing.*;

public class UserGraphSettings {
    public String getHeaderDisplayType() {
        String defaultVal = "Staggered";
        return UserSettings.getInstance().getPropValue("HeaderDisplayType", defaultVal);
    }

    public void setHeaderDisplayType(String headerType) {
        UserSettings.getInstance().updateProperties("HeaderDisplayType", headerType);
    }

    public int getTiltedAngle() {
        String defaultVal = "15";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("TiltAngle", defaultVal));
        } catch (NumberFormatException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nsaved tilt angle from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setTiltedAngle(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setTiltedAngle(int angle) {
        UserSettings.getInstance().updateProperties("TiltAngle", Integer.toString(angle));
    }

    public void setTiltedTopPaddingPercentage(int padding) {
        UserSettings.getInstance().updateProperties("TiltedTopPaddingPercentage", Integer.toString(padding));
    }

    public int getTiltedTopPadding() {
        String defaultVal = "20";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("TiltedTopPaddingPercentage", defaultVal));
        } catch (NumberFormatException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nsaved tilted view padding from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setTiltedTopPaddingPercentage(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public int getChartHeaderFontSize() {
        String defaultVal = "16";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("ChartHeaderFontSize", defaultVal));
        } catch (NumberFormatException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nfont size from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setChartHeaderFontSize(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setChartHeaderFontSize(int value) {
        UserSettings.getInstance().updateProperties("ChartHeaderFontSize", Integer.toString(value));
    }

    public int getChartTickFontSize() {
        String defaultVal = "14";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("ChartTickFontSize", defaultVal));
        } catch (NumberFormatException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \nfont size from storage.", "Error", JOptionPane.WARNING_MESSAGE);
            setChartTickFontSize(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setChartTickFontSize(int value) {
        UserSettings.getInstance().updateProperties("ChartTickFontSize", Integer.toString(value));
    }

    public int getChartNumTicks() {
        String defaultVal = "5";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("ChartNumTicks", defaultVal));
        } catch (NumberFormatException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \n number of ticks to display on graph", "Error", JOptionPane.WARNING_MESSAGE);
            setChartNumTicks(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setChartNumTicks(int value) {
        UserSettings.getInstance().updateProperties("ChartNumTicks", Integer.toString(value));
    }

    public double getChartAxisMax() {
        String defaultVal = "5";
        try {
            return Double.parseDouble(UserSettings.getInstance().getPropValue("ChartAxisMax", defaultVal));
        } catch (NumberFormatException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \n chart axis settings", "Error", JOptionPane.WARNING_MESSAGE);
            setChartAxisMax(Double.parseDouble(defaultVal));
            return Double.parseDouble(defaultVal);
        }
    }

    public void setChartAxisMax(double value) {
        UserSettings.getInstance().updateProperties("ChartAxisMax", Double.toString(value));
    }

    public double getChartAxisMin() {
        String defaultVal = "1.5";
        try {
            return Double.parseDouble(UserSettings.getInstance().getPropValue("ChartAxisMin", defaultVal));
        } catch (NumberFormatException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error loading the \n chart axis settings", "Error", JOptionPane.WARNING_MESSAGE);
            setChartAxisMin(Double.parseDouble(defaultVal));
            return Double.parseDouble(defaultVal);
        }

    }

    public void setChartAxisMin(double value) {
        UserSettings.getInstance().updateProperties("ChartAxisMin", Double.toString(value));
    }

    public boolean getChartZoom() {
        String defaultVal = "false";
        try {
            return Boolean.parseBoolean(UserSettings.getInstance().getPropValue("ChartZoom", defaultVal));
        } catch (NumberFormatException e) {
            setChartZoom(false);
            return false;
        }
    }

    public void setChartZoom(boolean value) {
        UserSettings.getInstance().updateProperties("ChartZoom", Boolean.toString(value));
    }

    public boolean getChartAbsolute() {
        String defaultVal = "false";
        try {
            return Boolean.parseBoolean(UserSettings.getInstance().getPropValue("ChartAbsolute", defaultVal));
        } catch (NumberFormatException e) {
            setChartAbsolute(false);
            return false;
        }
    }

    public void setChartAbsolute(boolean value) {
        UserSettings.getInstance().updateProperties("ChartAbsolute", Boolean.toString(value));
    }

    public boolean getChartFilterTextData() {
        String defaultVal = "false";
        try {
            return Boolean.parseBoolean(UserSettings.getInstance().getPropValue("ChartFilterTextData", defaultVal));
        } catch (NumberFormatException e) {
            setChartFilterTextData(false);
            return false;
        }
    }

    public void setChartFilterTextData(boolean value) {
        UserSettings.getInstance().updateProperties("ChartFilterTextData", Boolean.toString(value));
    }



    public boolean getChartShowFilterTrails() {
        String defaultVal = "false";
        try {
            return Boolean.parseBoolean(UserSettings.getInstance().getPropValue("ChartShowFilterTrails", defaultVal));
        } catch (NumberFormatException e) {
            setChartShowFilterTrails(false);
            return false;
        }
    }

    public void setChartShowFilterTrails(boolean value) {
        UserSettings.getInstance().updateProperties("ChartShowFilterTrails", Boolean.toString(value));
    }



    public int getAxesPerScreenWidth() {
        String defaultVal = "14";
        try {
            return Integer.parseInt(UserSettings.getInstance().getPropValue("AxesPerScreenWidth", defaultVal));
        } catch (NumberFormatException e) {
            setAxesPerScreenWidth(Integer.parseInt(defaultVal));
            return Integer.parseInt(defaultVal);
        }
    }

    public void setAxesPerScreenWidth(int value) {
        UserSettings.getInstance().updateProperties("AxesPerScreenWidth", Integer.toString(value));
    }

    public float getAxesThickness() {
        String defaultVal = "2";
        try {
            return Float.parseFloat(UserSettings.getInstance().getPropValue("AxesThickness", defaultVal));
        } catch (NumberFormatException e) {
            setAxesThickness(Float.parseFloat(defaultVal));
            return Float.parseFloat(defaultVal);
        }
    }

    public void setAxesThickness(float value) {
        UserSettings.getInstance().updateProperties("AxesThickness", Float.toString(value));
    }

    public float getChartLineThickness() {
        String defaultVal = "2";
        try {
            return Float.parseFloat(UserSettings.getInstance().getPropValue("ChartLineThickness", defaultVal));
        } catch (NumberFormatException e) {
            setChartLineThickness(Float.parseFloat(defaultVal));
            return Float.parseFloat(defaultVal);
        }
    }

    public void setChartLineThickness(float value) {
        UserSettings.getInstance().updateProperties("ChartLineThickness", Float.toString(value));
    }

    public void setFilterColour(int r, int g, int b) {
        String str = r + "," +  g + "," + b;
        UserSettings.getInstance().updateProperties("FilterColour", str);
    }

    public int[] getFilterColour() {
        int[] defaultVal = {165,165,165};
        String strDefault = defaultVal[0]  + "," +  defaultVal[1] + "," + defaultVal[2];
        try {
            String str = UserSettings.getInstance().getPropValue("FilterColour", strDefault);
            str = str.replaceAll("\\s+","");
            String[] tokens = str.split(",",-1);
            int[] out = new int[3];
            int i = 0;
            for (String token:tokens) {
                int intVal = Integer.parseInt(token);
                if (intVal < 0 || intVal > 255){
                    return defaultVal;
                }
                out[i] =intVal;
                i++;
            }
            return out;

        }
        catch (Exception e) {
            setFilterColour(defaultVal[0], defaultVal[1],defaultVal[2]);
            e.printStackTrace();
            return defaultVal;
        }
    }

    public void setChartColourWeights(float r, float g, float b) {
        String str = r + "," +  g + "," + b;
        UserSettings.getInstance().updateProperties("ChartColourWeights", str);
    }

    public float[] getChartColourWeights() {
        float[] defaultVal = {0,0,0};
        String strDefault = defaultVal[0]  + "," +  defaultVal[1] + "," + defaultVal[2];
        try {
            String str = UserSettings.getInstance().getPropValue("ChartColourWeights", strDefault);
            str = str.replaceAll("\\s+","");
            String[] tokens = str.split(",",-1);
            float[] out = new float[3];
            int i = 0;
            for (String token:tokens) {
                float floatVal = Float.parseFloat(token);
                if (floatVal < 0 || floatVal >5){
                    return defaultVal;
                }
                out[i] =floatVal;
                i++;
            }
            return out;

        } catch (Exception e) {
            setChartColourWeights(defaultVal[0], defaultVal[1],defaultVal[2]);
            return defaultVal;
        }
    }


}

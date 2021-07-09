package ParallelCoords.Settings;

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
        String defaultVal = "20";
        return Integer.parseInt(UserSettings.getInstance().getPropValue("TiltAngle", defaultVal));
    }


}

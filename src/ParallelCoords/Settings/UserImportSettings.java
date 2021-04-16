package ParallelCoords.Settings;

public class UserImportSettings {

    private String defaultDelimiter = ",";
    private String lastOpenedFile = null;

    public String getDelimiter(){
        return UserSettings.getInstance().getPropValue("delimiter", defaultDelimiter);
    }

    public void setDelimiter(String delimiter) {
        UserSettings.getInstance().updateProperties("delimiter", delimiter);
    }

    public void setLastOpenedFile(String lastOpenedFile) {
        UserSettings.getInstance().updateProperties("lastOpenedFile", lastOpenedFile);
    }

    public String getLastOpenedFile() {
        String defaultVal = System.getProperty("user.home");
        System.out.println(UserSettings.getInstance().getPropValue("lastOpenedFile", "ree"));
        return UserSettings.getInstance().getPropValue("lastOpenedFile", defaultVal);
    }
}

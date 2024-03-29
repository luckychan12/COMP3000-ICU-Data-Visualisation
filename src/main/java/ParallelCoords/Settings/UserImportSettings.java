package ParallelCoords.Settings;

public class UserImportSettings {

    public String getDelimiter() {
        String defaultDelimiter = ",";
        return UserSettings.getInstance().getPropValue("Delimiter", defaultDelimiter);
    }

    public void setDelimiter(String delimiter) {
        UserSettings.getInstance().updateProperties("Delimiter", delimiter);
    }

    public String getLastOpenedFile() {
        String defaultVal = System.getProperty("user.home");
        return UserSettings.getInstance().getPropValue("LastOpenedFile", defaultVal);
    }

    public void setLastOpenedFile(String lastOpenedFile) {
        UserSettings.getInstance().updateProperties("LastOpenedFile", lastOpenedFile);
    }
}

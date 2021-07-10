package ParallelCoords.Settings;

public class UserImportSettings {

    public String getDelimiter(){
        String defaultDelimiter = ",";
        return UserSettings.getInstance().getPropValue("Delimiter", defaultDelimiter);
    }

    public void setDelimiter(String delimiter) {
        UserSettings.getInstance().updateProperties("Delimiter", delimiter);
    }

    public void setLastOpenedFile(String lastOpenedFile) {
        UserSettings.getInstance().updateProperties("LastOpenedFile", lastOpenedFile);
    }

    public String getLastOpenedFile() {
        String defaultVal = System.getProperty("user.home");
        System.out.println(UserSettings.getInstance().getPropValue("LastOpenedFile", "none"));
        return UserSettings.getInstance().getPropValue("LastOpenedFile", defaultVal);
    }
}

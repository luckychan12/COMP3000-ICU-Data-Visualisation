package ParallelCoords.Settings;

public class UserSettings {
    private static final UserSettings instance = new UserSettings();

    private UserSettings(){}

    public static UserSettings getInstance(){
        return instance;
    }

    private String username = "bob";


    private UserImportSettings userImportSettings = new UserImportSettings();
    private UserGeneralSettings userGeneralSettings = new UserGeneralSettings();
    private UserGraphSettings userGraphSettings = new UserGraphSettings();

    public UserImportSettings getUserImportSettings()
    {
        return userImportSettings;
    }

    public UserGeneralSettings getUserGeneralSettings() {
        return userGeneralSettings;
    }

    public UserGraphSettings getUserGraphSettings() {
        return userGraphSettings;
    }

    public void setUserGeneralSettings(UserGeneralSettings userGeneralSettings) {
        this.userGeneralSettings = userGeneralSettings;
    }

    public void setUserGraphSettings(UserGraphSettings userGraphSettings) {
        this.userGraphSettings = userGraphSettings;
    }

    public void setUserImportSettings(UserImportSettings userImportSettings) {
        this.userImportSettings = userImportSettings;
    }
}

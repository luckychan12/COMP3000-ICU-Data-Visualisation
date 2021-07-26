package ParallelCoords.Settings;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class UserSettings {
    private static final UserSettings instance = new UserSettings();
    private Properties properties = new Properties();
    private UserImportSettings userImportSettings = new UserImportSettings();
    private UserGeneralSettings userGeneralSettings = new UserGeneralSettings();
    private UserGraphSettings userGraphSettings = new UserGraphSettings();
    private UserSettings() {
    }

    public static UserSettings getInstance() {
        return instance;
    }

    public UserImportSettings getUserImportSettings() {
        return userImportSettings;
    }

    public void setUserImportSettings(UserImportSettings userImportSettings) {
        this.userImportSettings = userImportSettings;
    }

    public UserGeneralSettings getUserGeneralSettings() {
        return userGeneralSettings;
    }

    public void setUserGeneralSettings(UserGeneralSettings userGeneralSettings) {
        this.userGeneralSettings = userGeneralSettings;
    }

    public UserGraphSettings getUserGraphSettings() {
        return userGraphSettings;
    }

    public void setUserGraphSettings(UserGraphSettings userGraphSettings) {
        this.userGraphSettings = userGraphSettings;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void updateProperties(String key, String value) {
        properties.setProperty(key, value);
        try (OutputStream outputStream = new FileOutputStream("config.properties")) {
            properties.store(outputStream, null);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String getPropValue(String key, String defaultVal) {
        return properties.getProperty(key, defaultVal);
    }


}

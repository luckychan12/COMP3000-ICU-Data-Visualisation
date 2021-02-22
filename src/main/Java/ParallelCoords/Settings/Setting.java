package ParallelCoords.Settings;

public abstract class Setting<Type> {
    private final Type defaultValue;
    private final Type currentValue;
    private final String settingName;
    private final SettingsType settingsType;


    Setting(Type defaultValue, Type currentValue, String settingName, SettingsType settingsType) {
        this.defaultValue = defaultValue;
        this.currentValue = currentValue;
        this.settingName = settingName;
        this.settingsType = settingsType;
    }

    public Type getDefault(){
        return defaultValue;
    }

    abstract void setCurrentToDefault();

    public Type getValue(){
        return currentValue;
    }
}
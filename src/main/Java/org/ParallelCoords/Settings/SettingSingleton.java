package org.ParallelCoords.Settings;

public class SettingSingleton {
    private static final SettingSingleton instance = new SettingSingleton();

    private SettingSingleton(){}

    public static SettingSingleton getInstance(){
        return instance;
    }

    private ImportConfig importConfig = new ImportConfig();

    public ImportConfig getImportConfig()
    {
        return importConfig;
    }

    public void setImportConfig(ImportConfig importConfig) {
        this.importConfig = importConfig;
    }
}

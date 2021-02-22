package ParallelCoords.Settings;

public class UserImportSettings {
    private String delimiter = ",";
    private Boolean readConsecutiveAsOne = false;
    private String lastOpenedFile = null;

    public String getDelimiter(){
        return delimiter;
    }
    public Boolean ReadConsecutiveAsOne(){
        return readConsecutiveAsOne;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void SetReadConsecutiveAsOne(Boolean readConsecutiveAsOne) {
        this.readConsecutiveAsOne = readConsecutiveAsOne;
    }

    public void setLastOpenedFile(String lastOpenedFile) {
        this.lastOpenedFile = lastOpenedFile;
    }

    public String getLastOpenedFile() {
        return lastOpenedFile;
    }
}

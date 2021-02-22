package ParallelCoords.Data;

import ParallelCoords.Main;
import ParallelCoords.Settings.UserSettings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
    private static final Data instance = new Data();
    private int dataID = 0;
    private ArrayList<DataTable> dataStore = new ArrayList<DataTable>();
    private String delimiter;
    private Data(){}
    public static Data getInstance(){
        return instance;
    }

    public void createData(Main mainWindow, String pathToInputData, boolean hasHeaders) throws IOException,NumberFormatException {
        UserSettings settings = UserSettings.getInstance();
        delimiter = settings.getUserImportSettings().getDelimiter();
        DataTable newData = new DataTable();
        BufferedReader reader = new BufferedReader(new FileReader(pathToInputData));
        String[] headers;
        String[] tokens = ((reader.readLine()).trim()).split(delimiter, -1);

        int i = 0;
        if (hasHeaders){
            for (String token : tokens){
               newData.addColumn(new DataColumn(i, token));
               i++;
           }
        }
        else {
            for (String token : tokens){
                DataColumn column = new DataColumn(i);
                DataEntity newEntity = new DataEntity();
                if (token.equals("")){
                    newEntity.setConfirmedValue(false);
                }
                else{
                    newEntity.setValue(Double.parseDouble(token));
                    newEntity.setConfirmedValue(true);
                }

                column.addEntity(newEntity);
                newData.addColumn(column);
                i++;
            }
        }
        dataStore.add(newData);
        dataID++;
        try {

            loadData(dataID -1, reader);

        }
        catch (IOException | NumberFormatException err){
            dataStore.remove(dataID -1);
            dataID--;

            throw err;
        }
    }


    private void loadData(int tableID, BufferedReader reader) throws IOException, NumberFormatException{
        String line;
        String[] tokens;
        while ((line = reader.readLine()) != null)
        {
            tokens = (line.trim()).split(delimiter, -1);
            int i = 0;
            if (tokens.length > 0) {
                for (String token : tokens){
                    DataEntity newEntity = new DataEntity();
                    if (token.equals("")){
                        newEntity.setConfirmedValue(false);
                    }
                    else{
                        newEntity.setValue(Double.parseDouble(token));
                        newEntity.setConfirmedValue(true);
                    }
                    dataStore.get(tableID).getColumn(i).addEntity(newEntity);
                    i++;
                }
            }
        }
    }

    public ArrayList<DataTable> getDataStore() {
        return dataStore;
    }
}

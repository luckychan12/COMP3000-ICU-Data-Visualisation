package ParallelCoords.Data;

import ParallelCoords.Settings.UserSettings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Data {
    private static final Data instance = new Data();
    private final ArrayList<DataTable> dataStore = new ArrayList<>();
    private int dataID = 0;
    private String delimiter;
    private int currID = 0;

    private Data() {
    }

    public static Data getInstance() {
        return instance;
    }

    public int getCurrID() {
        return currID;
    }


    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return Pattern.compile("-?\\d+(\\.\\d+)?").matcher(strNum).matches() || Pattern.compile("-?\\.\\d+").matcher(strNum).matches();
    }

    public void createData(String pathToInputData, boolean hasHeaders) throws Exception {
        UserSettings settings = UserSettings.getInstance();
        delimiter = settings.getUserImportSettings().getDelimiter();
        DataTable newData = new DataTable();

            BufferedReader reader = new BufferedReader(new FileReader(pathToInputData));
            String[] tokens = ((reader.readLine()).trim()).split(delimiter, -1);

            int currColumn = 0;
            if (hasHeaders) {
                for (String token : tokens) {
                    newData.addColumn(new DataColumn(currColumn, token));
                    currColumn++;
                    newData.setDefinedHeaders(true);
                }
            } else {
                newData.setDefinedHeaders(false);
                //Color lineColour = newData.genColour();
                for (String token : tokens) {
                    DataColumn column = new DataColumn(currColumn);

                    column.addEntity(addDataEntity(token));
                    newData.addColumn(column);
                    currColumn++;
                }
            }


            dataStore.add(newData);
            try {
                loadData(dataID, reader);
            } catch (Exception err) {
                dataStore.remove(dataStore.size()-1);
                throw err;
            }
        newData.initShowRecordList();
        currID = dataID;
        dataID++;
    }

    private DataEntity addDataEntity(String token) {
        DataEntity newEntity = new DataEntity();
        //newEntity.setLineColor(color);
        if (token.equals("")) {
            newEntity.setConfirmedValue(false);
            newEntity.setText(false);
        } else if (!(isNumeric(token))) {
            newEntity.setConfirmedValue(false);
            newEntity.setText(true);
            newEntity.setTextData(token);
        } else {
            newEntity.setValue(Double.parseDouble(token));
            newEntity.setText(false);
            newEntity.setConfirmedValue(true);
        }
        return newEntity;
    }


    private void loadData(int tableID, BufferedReader reader) throws IOException, NumberFormatException {
        String line;
        String[] tokens;
        int rowCount = 0;
        while ((line = reader.readLine()) != null) {
            tokens = (line.trim()).split(delimiter, -1);
            int currColumn = 0;
            if (tokens.length > 0) {
                // Color lineColour = dataStore.get(tableID).genColour();
                for (String token : tokens) {
                    DataColumn col = dataStore.get(tableID).getColumn(currColumn);
                    col.addEntity(addDataEntity(token));
                    currColumn++;
                }
            }
            rowCount++;
        }

        if (dataStore.get(tableID).hasDefinedHeaders()) {
            rowCount++;
        }
        dataStore.get(tableID).setNumRows(rowCount);
    }

    public ArrayList<DataTable> getDataStore() {
        return dataStore;
    }
}

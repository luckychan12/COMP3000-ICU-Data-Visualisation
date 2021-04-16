package ParallelCoords.DataListView;

//From xDat

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class DataCellEditor extends DefaultCellEditor {
    public DataCellEditor() {
        super(new JTextField());

    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        if (anEvent.getClass() == KeyEvent.class && ((KeyEvent) anEvent).getKeyCode() == (Event.ESCAPE)) {
            if (anEvent.getSource().getClass().equals(JTable.class)) {
                JTable srcTable = (JTable) anEvent.getSource();
                srcTable.getSelectionModel().clearSelection();
            }
            return false;
        } else {
            return !anEvent.getClass().equals(MouseEvent.class) || ((MouseEvent) anEvent).getClickCount() >= 2;
        }
    }

}

package ParallelCoords.Exceptions;

public class NoDataException extends Exception{
    public NoDataException() {
        super("No data has been loaded");
    }
}

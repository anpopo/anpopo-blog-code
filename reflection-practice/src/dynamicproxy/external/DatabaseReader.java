package dynamicproxy.external;

public interface DatabaseReader {
    int countRowsInTable(String tableName) throws InterruptedException;

    String[] readRow(String query) throws InterruptedException;
}

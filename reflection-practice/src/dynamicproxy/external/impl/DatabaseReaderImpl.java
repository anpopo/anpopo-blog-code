package dynamicproxy.external.impl;

import dynamicproxy.external.DatabaseReader;

public class DatabaseReaderImpl implements DatabaseReader {
    @Override
    public int countRowsInTable(String tableName) throws InterruptedException {
        System.out.printf("DatabaseReaderImpl - counting rows in table %s\n", tableName);

        Thread.sleep(1000);
        return 50;
    }

    @Override
    public String[] readRow(String query) throws InterruptedException {
        System.out.printf("DatabaseReaderImpl - Executing SQL query : %s\n", query);
        Thread.sleep(1500);
        return new String[]{"column1", "column2", "column3"};
    }

}

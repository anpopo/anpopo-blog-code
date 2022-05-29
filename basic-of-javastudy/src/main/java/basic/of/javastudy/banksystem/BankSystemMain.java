package basic.of.javastudy.banksystem;

import java.io.IOException;

public class BankSystemMain {
    public static void main(String[] args) throws IOException {

        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();

        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        bankStatementAnalyzer.analyze(args[0], bankStatementParser);
    }
}

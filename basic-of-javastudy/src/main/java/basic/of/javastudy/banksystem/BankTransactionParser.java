package basic.of.javastudy.banksystem;

/**
 * 응집도 향상 - 논리로 분리, 책임의 분리가 되지 않아 SRP 위배
 */
public class BankTransactionParser {

    public BankTransaction parseFromCSV(final String line) {
        throw new UnsupportedOperationException();
    }

    public BankTransaction parseFromJson(final String line) {
        throw new UnsupportedOperationException();
    }

    public BankTransaction parseFromXML(final String line) {
        throw new UnsupportedOperationException();
    }
}

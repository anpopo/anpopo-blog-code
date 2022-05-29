package basic.of.javastudy.banksystem;

import java.time.LocalDate;

/**
 * 응집도 향상 - 정보로 분리, 불필요한 의존 관계 증가
 */
public class BankTransactionDAO {

    public BankTransaction create(final LocalDate date, final double amount, final String description) {
        throw new UnsupportedOperationException();
    }

    public BankTransaction read(final long id) {
        throw new UnsupportedOperationException();
    }

    public BankTransaction update(final long id) {
        throw new UnsupportedOperationException();
    }

    public void delete(final BankTransaction bankTransaction) {
        throw new UnsupportedOperationException();
    }
}

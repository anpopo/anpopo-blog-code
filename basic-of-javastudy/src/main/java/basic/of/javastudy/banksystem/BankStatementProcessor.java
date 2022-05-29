package basic.of.javastudy.banksystem;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }
    /**
     * functional interface 사용으로 반복부와 로직을 분리
     */
    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0;
        for (BankTransaction bankTransaction : bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    /**
     * @return 총 입출금 내역 합계
     */
    public double calculateTotalAmount() {
        return this.summarizeTransactions((acc, bankTransaction) -> acc + bankTransaction.getAmount());
    }

    /**
     * @param month 월
     * @return 해당하는 달의 입출금 내역 합계
     */
    public double calculateTotalInMonth(final Month month) {
        return this.summarizeTransactions((acc, bankTransactions) -> bankTransactions.getDate().getMonth() == month ? acc + bankTransactions.getAmount() : acc);
    }

    /**
     * @param category 카테고리
     * @return 해당 카테고리의 입출금 내역의 합계
     */
    public double calculateTotalForCategory(final String category) {
        return this.summarizeTransactions((acc, bankTransactions) -> bankTransactions.getDescription().equals(category) ? acc + bankTransactions.getAmount() : acc);
    }

    /**
     * @param amount 금액
     * @return 특정 금액 이상의 모든 입출금 내역
     */
    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        return this.findTransactions((bankTransaction) -> bankTransaction.getAmount() >= amount);
    }

    /**
     * 비지니스 로직과 반복 로직의 결합을 제거 - 인터페이스를 이용
     * @param bankTransactionFilter 조건 - functional interface 인 필터의 구현체
     * @return 조건에 맞는 모든 입출금 내역
     */
    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        final List<BankTransaction> result = new ArrayList<>();

        for (BankTransaction bankTransaction : bankTransactions) {
            if (bankTransactionFilter.test(bankTransaction)) {
                result.add(bankTransaction);
            }
        }

        return result;
    }
}

package basic.of.javastudy.banksystem.processor;

import basic.of.javastudy.banksystem.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double accumulator, BankTransaction bankTransaction);
}

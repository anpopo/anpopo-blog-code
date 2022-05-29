package basic.of.javastudy.banksystem.processor;

import basic.of.javastudy.banksystem.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {

    boolean test(BankTransaction bankTransaction);
}

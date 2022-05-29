package basic.of.javastudy.banksystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class BankStatementCSVParserTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    void shouldParseOneCorrectLine() throws Exception {
        // given
        final String line = "30-01-2017,-50,Tesco";

        // when
        final BankTransaction result = statementParser.parseFrom(line);

        // then
        BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");

        final double tolerance = 0.0d;
        Assertions.assertEquals(expected.getDate(), result.getDate());
        Assertions.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assertions.assertEquals(expected.getDescription(), result.getDescription());

    }

}
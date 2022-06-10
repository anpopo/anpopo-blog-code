package basic.of.javastudy.documentmanagement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentManagementSystemTest {

    private final static String LETTER = "letter";

    private DocumentManagementSystem system = new DocumentManagementSystem();
    @Test
    public void shouldImportFile() throws Exception {
        system.importFile(LETTER);
        final Document document = onlyDocument();

    }

    @Test
    void shouldNotImportMissingFile() throws Exception {
        assertThrows(FileNotFoundException.class, () -> system.importFile("asdflkasdf.txt"));
    }

    private Document onlyDocument() {
        final List<Document> documents = system.contents();
        return documents.get(0);
    }

}
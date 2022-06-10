package basic.of.javastudy.documentmanagement;


import basic.of.javastudy.documentmanagement.exception.UnknownFileTypeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class DocumentManagementSystem {
    private final List<Document> documents = new ArrayList<>();
    private final List<Document> documentsView = unmodifiableList(documents);
    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public DocumentManagementSystem() {
        this.extensionToImporter.put("letter", new LetterImporter());
        this.extensionToImporter.put("report", new ReportImporter());
        this.extensionToImporter.put("jpg", new ImageImporter());
    }

    public void importFile(final String path) throws IOException {

        final File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(path);
        }

        final int separatorIndex = path.lastIndexOf(".");

        if (separatorIndex != -1) {
            if (separatorIndex == path.length()) {
                throw new UnknownFileTypeException(String.format("파일의 형식이 잘못되었습니다. %s", path));
            }

            final String extension = path.substring(separatorIndex + 1);
            Importer importer = extensionToImporter.get(extension);
            if (importer == null) {
                throw new UnknownFileTypeException(String.format("%s 확장자의 파일 관리자가 존재하지 않습니다.", extension));
            }

            final Document document = importer.importFile(file);
            documents.add(document);
        } else {
            throw new UnknownFileTypeException("no extension found for : " + path);
        }

    }

    public List<Document> contents() {
        return documentsView;
    }

    public List<Document> search(final String query) {
        return documents.stream()
                .filter(Query.parse(query))
                .collect(Collectors.toList());
    }
}

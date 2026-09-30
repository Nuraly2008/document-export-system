package documentexport.tests;
import org.junit.jupiter.api.Test;
import documentexport.abstraction.ReportDocument;
import documentexport.abstraction.TextDocument;
import documentexport.implementor.Exporter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentBridgeTest {

    static class FakeExporter implements Exporter {

        String content;
        String fileName;

        @Override
        public void export(String content, String fileName) {
            this.content = content;
            this.fileName = fileName;
        }
    }

    @Test
    void textDocumentDelegatesCorrectly() {

        FakeExporter exporter = new FakeExporter();

        TextDocument document =
                new TextDocument(
                        exporter,
                        "Hello"
                );

        document.export("text-file");

        assertEquals(
                "TEXT DOCUMENT\nHello",
                exporter.content
        );

        assertEquals(
                "text-file",
                exporter.fileName
        );
    }

    @Test
    void reportDocumentDelegatesCorrectly() {

        FakeExporter exporter = new FakeExporter();

        ReportDocument document =
                new ReportDocument(
                        exporter,
                        "Sales",
                        "Sales increased"
                );

        document.export("report-file");

        assertEquals(
                "REPORT\nTitle: Sales\nSales increased",
                exporter.content
        );

        assertEquals(
                "report-file",
                exporter.fileName
        );
    }
}
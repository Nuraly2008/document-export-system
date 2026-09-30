package documentexport.adapter;

import documentexport.exception.ExportException;
import documentexport.implementor.Exporter;
import documentexport.legacy.LegacyWordLibrary;

public class LegacyWordAdapter implements Exporter {

    private final LegacyWordLibrary legacyLibrary;

    public LegacyWordAdapter(LegacyWordLibrary legacyLibrary) {
        this.legacyLibrary = legacyLibrary;
    }

    @Override
    public void export(String content, String fileName) {

        char[] data = content.toCharArray();

        String path = fileName + ".doc";

        int mode = 1;

        int result = legacyLibrary.createDocument(
                data,
                mode,
                path
        );

        if (result == 10) {
            throw new ExportException("Cannot export empty document");
        }

        if (result == 20) {
            throw new ExportException("Invalid file path");
        }

        if (result == 30) {
            throw new ExportException("Invalid legacy export mode");
        }

        if (result != 0) {
            throw new ExportException("Unknown legacy export error");
        }
    }
}

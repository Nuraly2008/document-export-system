package documentexport.selector;

import documentexport.adapter.LegacyWordAdapter;
import documentexport.implementor.Exporter;
import documentexport.implementor.HtmlExporter;
import documentexport.implementor.PdfExporter;
import documentexport.legacy.LegacyWordLibrary;

public class ExporterSelector {

    public Exporter select(String format) {

        if (format == null) {
            throw new IllegalArgumentException("Format cannot be null");
        }

        String selected = format.trim().toLowerCase();

        if (selected.equals("pdf")) {
            return new PdfExporter();
        }

        if (selected.equals("html")) {
            return new HtmlExporter();
        }

        if (selected.equals("word")) {
            return new LegacyWordAdapter(
                    new LegacyWordLibrary()
            );
        }

        throw new IllegalArgumentException(
                "Unsupported format: " + format
        );
    }
}
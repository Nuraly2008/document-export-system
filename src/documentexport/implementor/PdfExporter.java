package documentexport.implementor;

public class PdfExporter implements Exporter {

    @Override
    public void export(String content, String fileName) {
        System.out.println("PDF EXPORT");
        System.out.println("File: " + fileName + ".pdf");
        System.out.println("Content: " + content);
    }
}
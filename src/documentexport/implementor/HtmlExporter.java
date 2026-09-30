package documentexport.implementor;

public class HtmlExporter implements Exporter {

    @Override
    public void export(String content, String fileName) {
        System.out.println("HTML EXPORT");
        System.out.println("File: " + fileName + ".html");
        System.out.println("Content: " + content);
    }
}
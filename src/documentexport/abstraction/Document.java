package documentexport.abstraction;

import documentexport.implementor.Exporter;

public abstract class Document {

    protected final Exporter exporter;
    protected String content;

    public Document(Exporter exporter, String content) {
        this.exporter = exporter;
        this.content = content;
    }

    public void export(String fileName) {
        String preparedContent = prepareContent();
        exporter.export(preparedContent, fileName);
    }

    protected abstract String prepareContent();
}
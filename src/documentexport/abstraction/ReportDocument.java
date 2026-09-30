package documentexport.abstraction;

import documentexport.implementor.Exporter;

public class ReportDocument extends Document {

    private final String title;

    public ReportDocument(Exporter exporter, String title, String content) {
        super(exporter, content);
        this.title = title;
    }

    @Override
    protected String prepareContent() {
        return "REPORT\n"
                + "Title: " + title + "\n"
                + content;
    }
}

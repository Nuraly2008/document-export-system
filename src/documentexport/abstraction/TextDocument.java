package documentexport.abstraction;

import documentexport.implementor.Exporter;

public class TextDocument extends Document {

    public TextDocument(Exporter exporter, String content) {
        super(exporter, content);
    }

    @Override
    protected String prepareContent() {
        return "TEXT DOCUMENT\n" + content;
    }
}
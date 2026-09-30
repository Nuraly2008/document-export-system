package documentexport;

import documentexport.abstraction.Document;
import documentexport.abstraction.ReportDocument;
import documentexport.abstraction.TextDocument;
import documentexport.implementor.Exporter;
import documentexport.selector.ExporterSelector;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ExporterSelector selector = new ExporterSelector();

        System.out.println("DOCUMENT EXPORT SYSTEM");

        System.out.println();
        System.out.println("Choose document type:");
        System.out.println("1 - Text");
        System.out.println("2 - Report");

        String documentType = scanner.nextLine();

        System.out.println();
        System.out.println("Enter document content:");

        String content = scanner.nextLine();

        System.out.println();
        System.out.println("Choose export format:");
        System.out.println("pdf / html / word");

        String format = scanner.nextLine();

        Exporter exporter = selector.select(format);

        Document document;

        if (documentType.equals("1")) {

            document = new TextDocument(
                    exporter,
                    content
            );

        } else {

            System.out.println();
            System.out.println("Enter report title:");

            String title = scanner.nextLine();

            document = new ReportDocument(
                    exporter,
                    title,
                    content
            );
        }

        System.out.println();
        System.out.println("Enter file name:");

        String fileName = scanner.nextLine();

        document.export(fileName);

        System.out.println();
        System.out.println("Export completed.");

        scanner.close();
    }
}
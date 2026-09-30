# Design Rationale

## 1. Problem Description

The project is a Document Export System.

The system works with different document types and allows them to be exported using different export implementations.

The current document types are:

- TextDocument
- ReportDocument

The current export implementations are:

- PdfExporter
- HtmlExporter
- LegacyWordAdapter

The purpose of the design is to allow document types and export implementations to evolve independently.

---

## 2. Why Bridge Is Used

The Bridge pattern separates the abstraction hierarchy from the implementation hierarchy.

In this project, the abstraction hierarchy is:

- Document
- TextDocument
- ReportDocument

The implementation hierarchy is based on:

- Exporter
- PdfExporter
- HtmlExporter
- LegacyWordAdapter

The Document class depends only on the Exporter interface.

This means that a document does not need to know which concrete exporter is being used.

For example, TextDocument can work with:

- PdfExporter
- HtmlExporter
- LegacyWordAdapter

The same is true for ReportDocument.

Without Bridge, we could end up creating many combinations such as:

- PdfTextDocument
- HtmlTextDocument
- WordTextDocument
- PdfReportDocument
- HtmlReportDocument
- WordReportDocument

This would create unnecessary subclass growth.

Bridge avoids this problem by separating the two dimensions.

---

## 3. Why Adapter Is Used

The system also needs to support an old Word export library called LegacyWordLibrary.

The new system expects exporters to implement:

```java
void export(String content, String fileName);

However, LegacyWordLibrary uses:
int createDocument(char[] data, int mode, String path);

The old library is incompatible with the Exporter interface.
The incompatibility is not only the method name.
There are several differences:
- the method name is different
- String is converted to char[]
- the number of parameters is different
- the parameter order is different
- the return type is different
- the old library uses integer error codes
- the new system uses exceptions
Because of these differences, LegacyWordLibrary cannot be used directly as an Exporter.
LegacyWordAdapter solves this problem.
It implements the Exporter interface and internally calls LegacyWordLibrary.
The adapter converts the input into the legacy format and translates legacy error codes into ExportException.
The source code of LegacyWordLibrary is not modified.
4. Why Bridge Alone Is Not Enough
Bridge allows the document hierarchy and exporter hierarchy to vary independently.
However, Bridge does not solve compatibility problems.
LegacyWordLibrary does not follow the Exporter contract.
It has a different method signature and a different failure mechanism.
Therefore Bridge alone cannot integrate LegacyWordLibrary into the system.
Adapter is required to make the legacy class compatible with the Exporter interface.
5. Why Adapter Alone Is Not Enough
Adapter can make LegacyWordLibrary compatible with Exporter.
However, Adapter alone does not separate document types from export implementations.
Without Bridge, every document type could become directly dependent on concrete exporters.
That would make the design harder to extend.
Bridge is needed to keep the two hierarchies independent.
Therefore both patterns solve different problems in the same system.
6. Failure Translation
LegacyWordLibrary uses integer error codes.
The current codes are:
- 0 — success
- 10 — empty document
- 20 — invalid path
- 30 — invalid mode
The adapter translates these values into meaningful ExportException errors.
For example:
- 10 becomes "Cannot export empty document"
- 20 becomes "Invalid file path"
- 30 becomes "Invalid legacy export mode"
This prevents legacy-specific error codes from leaking into the abstraction side of the system.
Document, TextDocument and ReportDocument do not know anything about these error codes.
7. Complexity Module
The project uses Dynamic Implementor Selection.
ExporterSelector selects the concrete exporter at runtime based on user input.
The supported inputs are:
- pdf
- html
- word
For example:
Exporter exporter = selector.select(format);

The selected implementation can be:
- PdfExporter
- HtmlExporter
- LegacyWordAdapter
The client does not directly hard-code the concrete implementation.
This allows the implementation to be selected dynamically while the program is running.
8. Open/Closed Principle
The design supports the Open/Closed Principle on both sides of the Bridge.
Adding a new document type
A new document type can extend Document.
For example:
public class InvoiceDocument extends Document {
}

Existing exporters do not need to be modified.
Adding a new exporter
A new exporter can implement Exporter.
For example:
public class MarkdownExporter implements Exporter {
}

Existing document classes do not need to be modified.
This means that both hierarchies can be extended independently.
9. Testing
JUnit 5 tests are included in the project.
DocumentBridgeTest checks normal delegation for:
- TextDocument
- ReportDocument
A fake Exporter implementation is used to verify that the documents delegate the prepared content correctly.
LegacyWordAdapterTest checks failure translation.
A test legacy implementation returns an error code and the test verifies that LegacyWordAdapter converts it into ExportException.
10. Limitation
The current system simulates exporting by printing information to the console.
It does not create real PDF, HTML or Word files.
A real production version would require external libraries for actual file generation.
This limitation keeps the project focused on the Bridge and Adapter design patterns.
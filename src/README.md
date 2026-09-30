# Document Export System

## Assignment 3 — Adapter and Bridge Patterns

This project demonstrates the use of the Bridge and Adapter design patterns in one system.

The main idea of the project is to allow different document types to be exported using different export implementations.

The system supports:

- Text documents
- Report documents

The available export formats are:

- PDF
- HTML
- Legacy Word format

---

## Project Structure

```text
src
└── documentexport
    ├── Main.java
    │
    ├── abstraction
    │   ├── Document.java
    │   ├── TextDocument.java
    │   └── ReportDocument.java
    │
    ├── implementor
    │   ├── Exporter.java
    │   ├── PdfExporter.java
    │   └── HtmlExporter.java
    │
    ├── adapter
    │   └── LegacyWordAdapter.java
    │
    ├── legacy
    │   └── LegacyWordLibrary.java
    │
    ├── exception
    │   └── ExportException.java
    │
    ├── selector
    │   └── ExporterSelector.java
    │
    └── tests
        ├── DocumentBridgeTest.java
        └── LegacyWordAdapterTest.java

Bridge Pattern
The Bridge pattern is used to separate document types from export implementations.
Abstraction
Document is the main abstraction.
It contains a reference to the Exporter interface.
protected final Exporter exporter;

Refined Abstractions
The project has two refined abstractions:
- TextDocument
- ReportDocument
Both classes extend Document.
Implementor
Exporter is the implementor interface.
public interface Exporter {
    void export(String content, String fileName);
}

Concrete Implementations
The project has three implementations:
- PdfExporter
- HtmlExporter
- LegacyWordAdapter
Because the document hierarchy and exporter hierarchy are separated, different combinations can be used.
For example:
TextDocument + PdfExporter

TextDocument + HtmlExporter

ReportDocument + PdfExporter

ReportDocument + LegacyWordAdapter

Adapter Pattern
The Adapter pattern is used for LegacyWordLibrary.
The old library does not implement the Exporter interface.
The new interface uses:
void export(String content, String fileName);

But the legacy library uses:
int createDocument(char[] data, int mode, String path);

The APIs are incompatible because they use:
- different method names
- different parameter types
- different parameter order
- different number of parameters
- different return types
- different error handling
LegacyWordLibrary returns integer error codes.
For example:
0  - success
10 - empty document
20 - invalid path
30 - invalid mode

LegacyWordAdapter converts the request from the Exporter interface into the format required by LegacyWordLibrary.
It also translates the legacy error codes into ExportException.
Dynamic Implementor Selection
The project uses Dynamic Implementor Selection as the required complexity module.
ExporterSelector chooses the correct exporter at runtime.
Supported input:
pdf
html
word

Example:
Exporter exporter = selector.select(format);

The client does not need to directly create a concrete exporter.
Example
Example input:
Choose document type:
1 - Text
2 - Report

1

Enter document content:
Hello World

Choose export format:
pdf / html / word

pdf

Enter file name:
test

Example output:
PDF EXPORT
File: test.pdf
Content: TEXT DOCUMENT
Hello World

Export completed.

Tests
The project contains JUnit 5 tests.
DocumentBridgeTest
Tests the delegation between the abstraction and implementor sides of the Bridge.
It checks:
- TextDocument
- ReportDocument
A fake exporter is used to verify that the correct content is passed to the implementation.
LegacyWordAdapterTest
Tests error translation in the Adapter.
The legacy implementation returns an error code and the adapter translates it into ExportException.
Design Patterns
Bridge
Used to separate document types from export implementations.
This allows both parts to change independently.
Adapter
Used to connect LegacyWordLibrary to Exporter without changing the legacy class.
Open/Closed Principle
The project follows the Open/Closed Principle.
A new document type can be added by extending:
Document

For example:
InvoiceDocument

A new exporter can be added by implementing:
Exporter

For example:
MarkdownExporter

Existing document classes do not need to be changed.
Technologies
- Java
- JUnit 5
- IntelliJ IDEA
- Bridge Pattern
- Adapter Pattern
Author
Nuraly Adilgereyev from SE-2530
Assignment 3 — Adapter and Bridge Design Patterns

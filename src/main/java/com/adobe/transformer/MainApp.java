package com.adobe.transformer;

import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public final class MainApp {

    public static void main(String[] args) throws IOException {
        final var pptSample = MainApp.class.getResourceAsStream("/ppt-sample.pptx");
        final var slideShow = new XMLSlideShow(pptSample);


        final var provider = new PrintStreamProvider(PrintStreamProvider.ProviderOption.WORD_DOCUMENT);
        final var printStream = provider.getPrintStream();

        final var document = new XWPFDocument();

        printContent(slideShow, printStream, document);
    }

    private static void printContent(XMLSlideShow powerPoint, PrintStream writer, XWPFDocument document) {
        final POIXMLProperties.CoreProperties properties = powerPoint.getProperties().getCoreProperties();
        final var title = properties.getTitle();

        writer.println("Title: " + title);

        for (var slide : powerPoint.getSlides()) {
            writer.println("Starting slide...");
            final List<XSLFShape> shapes = slide.getShapes();

            for (final var shape : shapes) {
                if (shape instanceof XSLFTextShape) {
                    final XSLFTextShape textShape = (XSLFTextShape) shape;
                    final var text = textShape.getText();
                    writer.println("Text: " + text);
                }
            }
        }
    }
}

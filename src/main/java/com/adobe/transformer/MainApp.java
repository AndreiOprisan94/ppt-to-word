package com.adobe.transformer;

import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import java.io.IOException;
import java.util.List;

public final class MainApp {

    public static void main(String[] args) throws IOException {
        final var pptSample = MainApp.class.getResourceAsStream("/ppt-sample.pptx");
        final var slideShow = new XMLSlideShow(pptSample);

        printContent(slideShow);
    }

    private static void printContent(XMLSlideShow powerPoint) {
        final POIXMLProperties.CoreProperties props = powerPoint.getProperties().getCoreProperties();
        final String title = props.getTitle();
        System.out.println("Title: " + title);

        for (XSLFSlide slide : powerPoint.getSlides()) {
            System.out.println("Starting slide...");
            final List<XSLFShape> shapes = slide.getShapes();

            for (final var shape : shapes) {
                if (shape instanceof XSLFTextShape) {
                    final XSLFTextShape textShape = (XSLFTextShape) shape;
                    final String text = textShape.getText();
                    System.out.println("Text: " + text);
                }
            }
        }
    }
}

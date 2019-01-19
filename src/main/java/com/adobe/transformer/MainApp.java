package com.adobe.transformer;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;

import java.io.IOException;
import java.util.List;

public final class MainApp {

    public static void main(String[] args) throws IOException {
        final var pptSample = MainApp.class.getResourceAsStream("/ppt-sample.pptx");
        final var slideShow = new XMLSlideShow(pptSample);

        final var slides = slideShow.getSlides();

        for (final var slide : slides) {
            if (slide.getNotes() != null) {
                printContent(slide);
            } else {
                System.out.println("Nothing found in slide: " + slide.getTitle());
            }
        }
    }

    private static void printContent(XSLFSlide slide) {
        final List<List<XSLFTextParagraph>> textParagraphs = slide.getNotes().getTextParagraphs();

        for (final var listTexParagraph : textParagraphs) {
            for (final var textParagraph : listTexParagraph) {
                System.out.println(textParagraph.getText());
            }
        }
    }
}

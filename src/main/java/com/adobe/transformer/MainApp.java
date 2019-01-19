package com.adobe.transformer;

import org.apache.poi.xslf.usermodel.XMLSlideShow;

import java.io.IOException;

public final class MainApp {

    public static void main(String[] args) throws IOException {
        final var pptSample = MainApp.class.getResourceAsStream("/ppt-sample.pptx");
        final var slideShow = new XMLSlideShow(pptSample);

        final var slides = slideShow.getSlides();

        for (final var slide : slides) {
            System.out.println(slide.getSlideName());
        }
    }
}

package com.adobe.transformer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

final class PrintStreamProvider {
    private final ProviderOption providerOption;

    PrintStreamProvider(ProviderOption providerOption) {
        this.providerOption = providerOption;
    }

    PrintStream getPrintStream() {
        switch (this.providerOption) {
            case TEXT_FILE:
                return getTextPrintStream();

            case WORD_DOCUMENT:
                return getWordPrintStream();

            default:
                return null;
        }
    }

    private PrintStream getTextPrintStream() {
        final Optional<OutputStream> output = getOutputStreamOf("output_file");

        return output.map(outputStream -> new PrintStream(outputStream))
                .orElseThrow(IllegalStateException::new);

    }

    private PrintStream getWordPrintStream() {
        return getOutputStreamOf("OutputFile.docx")
                .map(outputStream -> new PrintStream(outputStream))
                .orElseThrow(IllegalStateException::new);
    }

    private static Optional<OutputStream> getOutputStreamOf(String fileName) {
        try {
            final var path = Paths.get(fileName);
            final OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE);
            return Optional.of(outputStream);
        } catch (final IOException problemsWithPath) {
            return Optional.empty();
        }
    }

    enum ProviderOption {
        TEXT_FILE,
        WORD_DOCUMENT
    }
}

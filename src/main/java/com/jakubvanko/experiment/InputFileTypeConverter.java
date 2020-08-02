package com.jakubvanko.experiment;

import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.UrlValidator;
import picocli.CommandLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class InputFileTypeConverter implements CommandLine.ITypeConverter<File> {

    private final UrlValidator urlValidator;

    public InputFileTypeConverter() {
        urlValidator = new UrlValidator(new String[]{"http", "https"});
    }

    @Override
    public File convert(String s) throws Exception {
        File tempFile;
        if (urlValidator.isValid(s)) {
            URL url = new URL(s);
            tempFile = new File("temp.csv");
            System.out.println("Downloading dataset from a remote location");
            FileUtils.copyURLToFile(url, tempFile);
            System.out.println("Dataset downloaded successfully");
        } else {
            tempFile = new File(s);
        }
        if (tempFile.exists()) return tempFile;
        throw new FileNotFoundException();
    }
}

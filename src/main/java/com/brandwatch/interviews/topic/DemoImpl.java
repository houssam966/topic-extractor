package com.brandwatch.interviews.topic;

import java.io.File;
import java.io.IOException;

import com.brandwatch.interviews.topic.textProcessors.TextProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;
import com.brandwatch.interviews.topic.printers.TopicResultsPrinter;

@Component
public class DemoImpl implements Demo {

    @Autowired
    private TextProvider provider;
    @Autowired
    private TopicExtractor extractor;
    @Autowired
    private TopicResultsPrinter printer;
    @Autowired
    private TextProcessor textProcessor;

    public void runDemo(File file) {

        try {
            String inputText = provider.readText(file);
            String normalisedString = textProcessor.normalise(inputText);
            TopicResults results = extractor.extract(normalisedString);
            printer.print(results);
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("Failed");
        }
    }
}

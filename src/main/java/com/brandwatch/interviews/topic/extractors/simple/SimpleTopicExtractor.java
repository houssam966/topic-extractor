package com.brandwatch.interviews.topic.extractors.simple;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.springframework.stereotype.Component;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;

import java.io.IOException;
import java.io.InputStream;

@Component
public class SimpleTopicExtractor implements TopicExtractor {

    public TopicResults extract(String inputText) throws IOException {
        InputStream inputStream = getClass()
                .getResourceAsStream("/models/en-token.bin");
        TokenizerModel model = new TokenizerModel(inputStream);
        TokenizerME tokenizer = new TokenizerME(model);
        String[] tokens = tokenizer.tokenize(inputText);

        TopicResults results = new TopicResults();
        //String[] words = inputText.split(" ");
        for (String token : tokens) {
            Topic topic = new Topic();
            topic.setLabel(token);
            results.addTopic(topic);
        }
        return results;
    }
}

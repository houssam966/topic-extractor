package com.brandwatch.interviews.topic.extractors.simple;

import com.aliasi.tokenizer.EnglishStopTokenizerFactory;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;

import org.springframework.stereotype.Component;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicExtractor;
import com.brandwatch.interviews.topic.extractors.TopicResults;

@Component
public class SimpleTopicExtractor implements TopicExtractor {

    public TopicResults extract(String normalisedString) {

        //remove stop words from the normalised string
        TokenizerFactory factory = IndoEuropeanTokenizerFactory.INSTANCE;
        factory = new EnglishStopTokenizerFactory(factory);
        Tokenizer tokenizer = factory.tokenizer(normalisedString.toCharArray(), 0, normalisedString.length());

        TopicResults results = new TopicResults();
        for(String word: tokenizer){
            Topic topic = new Topic();
            topic.setLabel(word);
            results.addTopic(topic);
        }
        return results;
    }

}

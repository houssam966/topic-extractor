package com.brandwatch.interviews.topic.extractors.simple;

import com.aliasi.tokenizer.EnglishStopTokenizerFactory;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;
import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
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
        //Split the string into tokens using TokenizerME
        String[] tokens = tokenize(inputText);
        //Get lemmas from the tokens to normalise words
        String[] lemmas = lemmatize(tokens);

        //Build the normalised String using the lemmas
        String normalisedString = "";
        int i = 0;
        for (String word: lemmas) {
            //When a word doesn't have a lemma, put the original token instead
            if(word.equals("O")) normalisedString+= tokens[i] + " ";
            else normalisedString+= word + " ";
            i++;
        }

        //remove all punctuation marks
        normalisedString = normalisedString.replaceAll("\\p{Punct}", "");
        //normalise possessive nouns
        normalisedString = normalisedString.replaceAll("’s", "");

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

    private String[] tokenize(String text) throws IOException {
        InputStream inputStream = getClass()
                .getResourceAsStream("/models/en-token.bin");
        TokenizerModel model = new TokenizerModel(inputStream);
        TokenizerME tokenizer = new TokenizerME(model);

        String[] tokens = tokenizer.tokenize(text);
        return tokens;
    }

    private String[] lemmatize(String[] tokens) throws IOException {
        InputStream inputStreamPOSTagger = getClass()
                .getResourceAsStream("/models/en-pos-maxent.bin");
        POSModel posModel = new POSModel(inputStreamPOSTagger);
        POSTaggerME posTagger = new POSTaggerME(posModel);
        String tags[] = posTagger.tag(tokens);
        InputStream dictLemmatizer = getClass()
                .getResourceAsStream("/models/en-lemmatizer.txt");
        DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(
                dictLemmatizer);
        String[] lemmas = lemmatizer.lemmatize(tokens, tags);
        return lemmas;
    }
}

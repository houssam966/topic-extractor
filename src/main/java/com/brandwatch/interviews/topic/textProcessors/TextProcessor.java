package com.brandwatch.interviews.topic.textProcessors;

import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class TextProcessor {

    /**
     * This method takes in a string and returns the normalised version of the string
     * ready for extracting
     * @param text text to be normalised
     * @return normalised text
     * @throws IOException
     */
    public String normalise(String text) throws IOException {
        //filter out special characters
        String filteredText = filterText(text);

        //Split the string into tokens using TokenizerME
        String[] tokens = tokenize(filteredText);
        //Get lemmas from the tokens to normalise words
        String[] lemmas = lemmatize(tokens);

        //Build the normalised String using the lemmas
        String normalisedString = buildNormalisedString(lemmas, tokens);

        return normalisedString;
    }

    /**
     * This method breaks down the text into tokens using
     * Apache OpenNLP model.
     * @param text
     * @return
     * @throws IOException
     */
    private String[] tokenize(String text) throws IOException {
        InputStream inputStream = getClass()
                .getResourceAsStream("/models/en-token.bin");
        TokenizerModel model = new TokenizerModel(inputStream);
        TokenizerME tokenizer = new TokenizerME(model);

        String[] tokens = tokenizer.tokenize(text);
        return tokens;
    }

    /**
     * This method extract lemmas for each word in a string array
     * @param tokens extracted from the text
     * @return array of lemmas
     * @throws IOException
     */
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

    /**
     * Using the lemmas and tokens provided, this method generates
     * the normalised string.
     * Since some words (like proper nouns) don't have lemmas, the normalised string
     * will contain the original token instead
     * @param lemmas Array of lemmas extracted from the text
     * @param tokens Array of tokens extracted from the text
     * @return normalised string with every word in its original form
     */
    private String buildNormalisedString(String[] lemmas, String[] tokens){
        String normalisedString = "";
        int i = 0;
        for (String word: lemmas) {
            //When a word doesn't have a lemma, put the original token instead
            if(word.equals("O")) normalisedString+= tokens[i] + " ";
            else normalisedString+= word + " ";
            i++;
        }
        return normalisedString;
    }

    /**
     * This method filters out punctuation characters as well as possessive apostrophe s
     * @param text the text to be filtered
     * @return filtered text without punctuation and apostrophe s
     */
    private String filterText(String text){
        //remove all punctuation marks
        text = text.replaceAll("\\p{Punct}", "");
        //normalise possessive nouns
        text = text.replaceAll("’s", "");
        return text;
    }
}

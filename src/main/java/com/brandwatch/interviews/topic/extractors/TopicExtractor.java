package com.brandwatch.interviews.topic.extractors;

import java.io.IOException;

public interface TopicExtractor {
    TopicResults extract(String inputText) throws IOException;
}

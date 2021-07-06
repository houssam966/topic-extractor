package com.brandwatch.interviews.topic.extractors;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;

public class TopicResults {
    private final Multiset<Topic> topics = HashMultiset.create();

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public ImmutableMultiset<Topic> getTopics() {
        return ImmutableMultiset.copyOf(topics);
    }
}

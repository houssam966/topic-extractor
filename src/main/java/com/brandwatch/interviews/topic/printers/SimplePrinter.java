package com.brandwatch.interviews.topic.printers;

import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multisets;

import com.brandwatch.interviews.topic.extractors.Topic;
import com.brandwatch.interviews.topic.extractors.TopicResults;

@Component
public class SimplePrinter implements TopicResultsPrinter {

    public void print(TopicResults results) {
        ImmutableMultiset<Topic> resultBreakdown = results.getTopics();
        for (Topic topic : Iterables.limit(Multisets.copyHighestCountFirst(resultBreakdown).elementSet(), 30)) {
            System.out.println(topic.getLabel() + " " + resultBreakdown.count(topic));
        }
    }
}

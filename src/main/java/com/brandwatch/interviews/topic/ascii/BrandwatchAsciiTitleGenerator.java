package com.brandwatch.interviews.topic.ascii;

import org.springframework.stereotype.Component;

@Component
public class BrandwatchAsciiTitleGenerator implements AsciiTitleGenerator {
    public String buildTitle() {
        StringBuilder builder = new StringBuilder();
        builder.append(" _______  ______    _______  __    _  ______   _     _  _______  _______  _______  __   __ \n");
        builder.append("|  _    ||    _ |  |   _   ||  |  | ||      | | | _ | ||   _   ||       ||       ||  | |  |\n");
        builder.append("| |_|   ||   | ||  |  |_|  ||   |_| ||  _    || || || ||  |_|  ||_     _||       ||  |_|  |\n");
        builder.append("|       ||   |_||_ |       ||       || | |   ||       ||       |  |   |  |       ||       |\n");
        builder.append("|  _   | |    __  ||       ||  _    || |_|   ||       ||       |  |   |  |      _||       |\n");
        builder.append("| |_|   ||   |  | ||   _   || | |   ||       ||   _   ||   _   |  |   |  |     |_ |   _   |\n");
        builder.append("|_______||___|  |_||__| |__||_|  |__||______| |__| |__||__| |__|  |___|  |_______||__| |__|\n");
        builder.append(" _______  _______  _______  ___   _______                                                  \n");
        builder.append("|       ||       ||       ||   | |       |                                                 \n");
        builder.append("|_     _||   _   ||    _  ||   | |       |                                                 \n");
        builder.append("  |   |  |  | |  ||   |_| ||   | |       |                                                 \n");
        builder.append("  |   |  |  |_|  ||    ___||   | |      _|                                                 \n");
        builder.append("  |   |  |       ||   |    |   | |     |_                                                  \n");
        builder.append("  |___|  |_______||___|    |___| |_______|                                                 \n");
        builder.append(" ______   _______  __   __  _______                                                        \n");
        builder.append("|      | |       ||  |_|  ||       |                                                       \n");
        builder.append("|  _    ||    ___||       ||   _   |                                                       \n");
        builder.append("| | |   ||   |___ |       ||  | |  |                                                       \n");
        builder.append("| |_|   ||    ___||       ||  |_|  |                                                       \n");
        builder.append("|       ||   |___ | ||_|| ||       |                                                       \n");
        builder.append("|______| |_______||_|   |_||_______|");
        return builder.toString();
    }
}

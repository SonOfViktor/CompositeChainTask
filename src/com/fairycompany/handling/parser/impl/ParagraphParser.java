package com.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.CompositeType;
import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.entity.impl.TextComposite;
import com.fairycompany.handling.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements TextParser {
    private static final String SENTENCE_REGEX = ".+?([\\.]{3}|[\\.!?])($|\\s)";
    private static final String NEXT_LINE_REGEX = "\\r\\n";
    private static final String SPACE_REPLACEMENT = " ";
    private TextParser sentenceParser = new SentenceParser();

    @Override
    public TextComponent parse(String paragraph) {
        TextComposite paragraphComposite = new TextComposite(CompositeType.PARAGRAPH);
        Pattern pattern = Pattern.compile(SENTENCE_REGEX, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(paragraph);

        while (matcher.find()) {
            String sentence = matcher.group();
            sentence = sentence.replaceAll(NEXT_LINE_REGEX, SPACE_REPLACEMENT).trim();
            TextComponent sentenceComposite = sentenceParser.parse(sentence);
            paragraphComposite.add(sentenceComposite);
        }

        return paragraphComposite;
    }
}

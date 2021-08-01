package com.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.CompositeType;
import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.entity.impl.TextComposite;
import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements TextParser {
    private static final String SENTENCE_REGEX = ".+?([.]{3}|[.!?])($|\\s)";
    private TextParser sentenceParser = new SentenceParser();

    @Override
    public TextComponent parse(String paragraph) throws CompositeException {
        if (paragraph == null || paragraph.isBlank()) {
            throw new CompositeException("Given text is null or blank");
        }

        TextComposite paragraphComposite = new TextComposite(CompositeType.SENTENCE);
        Pattern pattern = Pattern.compile(SENTENCE_REGEX, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(paragraph);

        while (matcher.find()) {
            String sentence = matcher.group();
            TextComponent sentenceComposite = sentenceParser.parse(sentence);
            paragraphComposite.add(sentenceComposite);
        }

        return paragraphComposite;
    }
}

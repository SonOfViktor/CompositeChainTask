package com.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.CompositeType;
import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.entity.impl.TextComposite;
import com.fairycompany.handling.parser.TextParser;

public class SentenceParser implements TextParser {
    private static final String SENTENCE_DELIMITER_REGEX = "\\s";
    private TextParser lexemeParser = new LexemeParser();

    @Override
    public TextComponent parse(String sentence) {
        String[] lexemes = sentence.split(SENTENCE_DELIMITER_REGEX);
        TextComposite sentenceComposite = new TextComposite(CompositeType.SENTENCE);

        for (String lexeme : lexemes) {
            TextComponent lexemeComposite = lexemeParser.parse(lexeme);
            sentenceComposite.add(lexemeComposite);
        }

        return sentenceComposite;
    }
}

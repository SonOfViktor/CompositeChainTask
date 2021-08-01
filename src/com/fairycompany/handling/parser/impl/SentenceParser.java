package com.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.CompositeType;
import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.entity.impl.Punctuation;
import com.fairycompany.handling.entity.impl.TextComposite;
import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.parser.TextParser;

public class SentenceParser implements TextParser {
    private static final String SENTENCE_DELIMITER_REGEX = "\\s+";
    private static final String PUNCTUATION_REGEX = "\\p{Punct}";
    private TextParser lexemeParser = new LexemeParser();

    @Override
    public TextComponent parse(String sentence) throws CompositeException {
        if (sentence == null || sentence.isBlank()) {
            throw new CompositeException("Given text is null or blank");
        }

        String[] lexemes = sentence.trim().split(SENTENCE_DELIMITER_REGEX);
        TextComposite sentenceComposite = new TextComposite(CompositeType.LEXEME);

        for (String lexeme : lexemes) {
            if(lexeme.matches(PUNCTUATION_REGEX)) {
                sentenceComposite.add(new Punctuation(lexeme.charAt(0)));
            } else {
                TextComponent lexemeComposite = lexemeParser.parse(lexeme);
                sentenceComposite.add(lexemeComposite);
            }
        }

        return sentenceComposite;
    }
}

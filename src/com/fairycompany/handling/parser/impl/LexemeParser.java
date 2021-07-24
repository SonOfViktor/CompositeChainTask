package com.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.CompositeType;
import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.entity.impl.Punctuation;
import com.fairycompany.handling.entity.impl.TextComposite;
import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {
    private static final String LEXEME_REGEX = "(\\S+?)([,.!?]|(\\.{3}))?($|\\s)";
    private static final String PUNCTUATION_REGEX = "\\p{Punct}";
    private TextParser wordParser = new WordParser();

    @Override
    public TextComponent parse(String lexeme) throws CompositeException {
        TextComposite lexemeComposite = new TextComposite(CompositeType.WORD);
        Pattern pattern = Pattern.compile(LEXEME_REGEX);
        Matcher matcher = pattern.matcher(lexeme);

        while (matcher.find()) {
            String word = matcher.group(1);
            String punctuation = matcher.group(2);

            if (word.matches(PUNCTUATION_REGEX)) {
                lexemeComposite.add(new Punctuation(word.charAt(0)));
            } else {
                TextComponent wordComposite = wordParser.parse(word);
                lexemeComposite.add(wordComposite);
            }

            if (punctuation != null) {
                lexemeComposite.add(new Punctuation(punctuation.charAt(0)));
            }

        }
        return lexemeComposite;
    }


}

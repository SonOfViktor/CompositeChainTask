package com.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.CompositeType;
import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.entity.impl.Punctuation;
import com.fairycompany.handling.entity.impl.TextComposite;
import com.fairycompany.handling.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {
    private static final String LEXEME_REGEX = "(.+?)([,\\.!?]|(\\.{3}))?$";
    private TextParser wordParser = new WordParser();

    @Override
    public TextComponent parse(String lexeme) {
        TextComposite lexemeComposite = new TextComposite(CompositeType.LEXEME);
        Pattern pattern = Pattern.compile(LEXEME_REGEX);
        Matcher matcher = pattern.matcher(lexeme);

        while (matcher.find()) {
            String word = matcher.group(1);
            String punctuation = matcher.group(2);

            TextComponent wordComposite = wordParser.parse(word);
            lexemeComposite.add(wordComposite);

            if (punctuation != null) {
                for (int i = 0; i < punctuation.length(); i++) {
                    lexemeComposite.add(new Punctuation(punctuation.charAt(i)));
                }
            }
        }
        return lexemeComposite;
    }


}

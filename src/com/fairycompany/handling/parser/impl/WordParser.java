package com.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.CompositeType;
import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.entity.impl.Digit;
import com.fairycompany.handling.entity.impl.Letter;
import com.fairycompany.handling.entity.impl.Punctuation;
import com.fairycompany.handling.entity.impl.TextComposite;
import com.fairycompany.handling.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements TextParser {
    private static final String SYMBOL_REGEX = ".";

    @Override
    public TextComponent parse(String word) {
        TextComposite wordComposite = new TextComposite(CompositeType.WORD);
        Pattern pattern = Pattern.compile(SYMBOL_REGEX);
        Matcher matcher = pattern.matcher(word);

        while (matcher.find()) {
            // todo make Creator and carry out regexes
            String symbol = matcher.group();
            if (symbol.matches("[\\p{Alpha}А-Яа-я]")) {
                wordComposite.add(new Letter(symbol.charAt(0)));
            }

            if (symbol.matches("\\p{Digit}")) {
                wordComposite.add(new Digit(symbol.charAt(0)));
            }

            if (symbol.matches("\\p{Punct}")) {
                wordComposite.add(new Punctuation(symbol.charAt(0)));
            }
        }

        return wordComposite;
    }
}

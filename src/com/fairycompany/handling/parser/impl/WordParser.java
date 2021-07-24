package com.fairycompany.handling.parser.impl;

import com.fairycompany.handling.creator.SymbolCreator;
import com.fairycompany.handling.entity.CompositeType;
import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.entity.impl.TextComposite;
import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements TextParser {
    private static final String SYMBOL_REGEX = "\\S";

    @Override
    public TextComponent parse(String word) throws CompositeException {
        SymbolCreator symbolCreator = new SymbolCreator();
        TextComposite wordComposite = new TextComposite(CompositeType.SYMBOL);
        Pattern pattern = Pattern.compile(SYMBOL_REGEX);
        Matcher matcher = pattern.matcher(word);

        while (matcher.find()) {
            String symbol = matcher.group();

            TextComponent symbolComponent = symbolCreator.createSymbol(symbol);

            wordComposite.add(symbolComponent);
        }

        return wordComposite;
    }
}

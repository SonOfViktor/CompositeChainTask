package com.fairycompany.handling.creator;

import com.fairycompany.handling.entity.Symbol;
import com.fairycompany.handling.entity.impl.Digit;
import com.fairycompany.handling.entity.impl.Letter;
import com.fairycompany.handling.entity.impl.Punctuation;
import com.fairycompany.handling.exception.CompositeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolCreator {
    private static Logger logger = LogManager.getLogger();
    private static final String LETTER_REGEX = "[\\p{Alpha}А-Яа-я]";
    private static final String DIGIT_REGEX = "\\p{Digit}";
    private static final String PUNCTUATION_REGEX = "[\\p{Punct}“”]";

    public Symbol createSymbol(String oneSymbol) throws CompositeException {
        Symbol symbol;

        if (oneSymbol.matches(LETTER_REGEX)) {
            symbol = new Letter(oneSymbol.charAt(0));
        } else if (oneSymbol.matches(DIGIT_REGEX)) {
            symbol = new Digit(oneSymbol.charAt(0));
        } else if (oneSymbol.matches(PUNCTUATION_REGEX)) {
            symbol = new Punctuation(oneSymbol.charAt(0));
        } else {
            throw new CompositeException("Symbol " + oneSymbol + " is unknown");
        }

        return symbol;
    }
}

package test.fairycompany.handling.creator;

import com.fairycompany.handling.creator.SymbolCreator;
import com.fairycompany.handling.entity.Symbol;
import com.fairycompany.handling.entity.impl.Digit;
import com.fairycompany.handling.entity.impl.Letter;
import com.fairycompany.handling.entity.impl.Punctuation;
import com.fairycompany.handling.exception.CompositeException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SymbolCreatorTest {
    SymbolCreator symbolCreator;

    @BeforeMethod
    public void setUp() {
        symbolCreator = new SymbolCreator();
    }

    @Test
    public void testCreateEnglishLetter() throws CompositeException {
        String letter = "D";

        Symbol letterSymbol = symbolCreator.createSymbol(letter);

        assertTrue(letterSymbol instanceof Letter);
    }

    @Test
    public void testCreateRussianLetter() throws CompositeException {
        String letter = "я";

        Symbol letterSymbol = symbolCreator.createSymbol(letter);

        assertTrue(letterSymbol instanceof Letter);
    }

    @Test
    public void testCreatePunctuation() throws CompositeException {
        String punctuation = ":";

        Symbol punctuationSymbol = symbolCreator.createSymbol(punctuation);

        assertTrue(punctuationSymbol instanceof Punctuation);
    }

    @Test
    public void testCreateDigit() throws CompositeException {
        String digit = "9";

        Symbol digitSymbol = symbolCreator.createSymbol(digit);

        assertTrue(digitSymbol instanceof Digit);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testCreateManySymbols() throws CompositeException {
        String symbols = "ab";
        Symbol manySymbols = symbolCreator.createSymbol(symbols);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testCreateEmptySymbol() throws CompositeException {
        String empty = "";
        Symbol emptySymbol = symbolCreator.createSymbol(empty);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testCreateSpaceSymbol() throws CompositeException {
        String space = " ";
        Symbol spaceSymbol = symbolCreator.createSymbol(space);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testCreateUnknownSymbol() throws CompositeException {
        String unknown = "ξ";
        Symbol unknownSymbol = symbolCreator.createSymbol(unknown);
    }
}
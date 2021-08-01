package test.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.parser.impl.LexemeParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LexemeParserTest {
    LexemeParser lexemeParser;

    @BeforeClass
    public void setUp() {
        lexemeParser = new LexemeParser();
    }

    @Test
    public void testParse() throws CompositeException {
        String document = """
                    It has survived - not only (five) centuries, but more-or-less the; leap into distribution
                ob.toString(a?b:c) essentially unchanged Falcon9...\
                """;

        String actual = lexemeParser.parse(document).getComponents().toString();

        String expected = """
                [It, has, survived, -, not, only, (five), centuries, ,, but, more-or-less, the, ;, leap, into, \
                distribution, ob.toString(a?b:c), essentially, unchanged, Falcon9, ., ., .]\
                """;

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testNullDocumentParse() throws CompositeException {
        TextComponent lexemeComponent = lexemeParser.parse(null);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testEmptyDocumentParse() throws CompositeException {
        String document = "";
        TextComponent lexemeComponent = lexemeParser.parse(document);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testBlankDocumentParse() throws CompositeException {
        String document = "   \n  \t";
        TextComponent lexemeComponent = lexemeParser.parse(document);
    }
}
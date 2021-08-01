package test.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.parser.impl.SentenceParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SentenceParserTest {
    SentenceParser sentenceParser;

    @BeforeClass
    public void beforeClass() {
        sentenceParser = new SentenceParser();
    }

    @Test
    public void testParse() throws CompositeException {
        String document = """
                    It has survived - not only (five) centuries, but more-or-less the leap into distribution
                ob.toString(a?b:c) essentially unchanged Falcon9...\
                """;

        String actual = sentenceParser.parse(document).getComponents().toString();

        String expected = """
                [It, has, survived, -, not, only, (five), centuries,, but, more-or-less, the, leap, into, \
                distribution, ob.toString(a?b:c), essentially, unchanged, Falcon9...]\
                """;

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testNullDocumentParse() throws CompositeException {
        TextComponent sentenceComponent = sentenceParser.parse(null);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testEmptyDocumentParse() throws CompositeException {
        String document = "";
        TextComponent sentenceComponent = sentenceParser.parse(document);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testBlankDocumentParse() throws CompositeException {
        String document = "   \n  \t";
        TextComponent sentenceComponent = sentenceParser.parse(document);
    }
}
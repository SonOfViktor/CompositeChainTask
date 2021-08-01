package test.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.parser.impl.WordParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WordParserTest {
    WordParser wordParser;

    @BeforeClass
    public void setUp() {
        wordParser = new WordParser();
    }

    @Test
    public void testParse() throws CompositeException {
        String document = """
                    It survived - (five) centuries, but more-or-less ob.toString(a?b:c) essentially Falcon9...\
                """;

        String actual = wordParser.parse(document).getComponents().toString();

        String expected = """
                [I, t, s, u, r, v, i, v, e, d, -, (, f, i, v, e, ), c, e, n, t, u, r, i, e, s, ,, b, u, t, \
                m, o, r, e, -, o, r, -, l, e, s, s, o, b, ., t, o, S, t, r, i, n, g, (, a, ?, b, :, c, ), \
                e, s, s, e, n, t, i, a, l, l, y, F, a, l, c, o, n, 9, ., ., .]\
                """;

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testNullDocumentParse() throws CompositeException {
        TextComponent wordComponent = wordParser.parse(null);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testEmptyDocumentParse() throws CompositeException {
        String document = "";
        TextComponent wordComponent = wordParser.parse(document);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testBlankDocumentParse() throws CompositeException {
        String document = "   \n  \t";
        TextComponent wordComponent = wordParser.parse(document);
    }
}
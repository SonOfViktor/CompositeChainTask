package test.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.parser.impl.ParagraphParser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ParagraphParserTest {
    ParagraphParser paragraphParser;

    @BeforeMethod
    public void setUp() {
        paragraphParser = new ParagraphParser();
    }

    @Test
    public void testParse() throws CompositeException {
        String document = """
                    It has survived - not only (five) centuries, but more-or-less the leap into distribution
                ob.toString(a?b:c) essentially unchanged Falcon9... It is a long a!=b established fact "!." \s
                that a reader will be distracted by Lorem Ipsum content of a page when looking at its layout?\
                """;

        String actual = paragraphParser.parse(document).getComponents().toString();

        String expected = """
                [It has survived - not only (five) centuries, but more-or-less the leap into distribution \
                ob.toString(a?b:c) essentially unchanged Falcon9... , \
                It is a long a!=b established fact "!." that a reader will be distracted by Lorem Ipsum \
                content of a page when looking at its layout? ]\
                """;

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testNullDocumentParse() throws CompositeException {
        TextComponent paragraphComponent = paragraphParser.parse(null);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testEmptyDocumentParse() throws CompositeException {
        String document = "";
        TextComponent paragraphComponent = paragraphParser.parse(document);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testBlankDocumentParse() throws CompositeException {
        String document = "   \n  \t";
        TextComponent paragraphComponent = paragraphParser.parse(document);
    }
}
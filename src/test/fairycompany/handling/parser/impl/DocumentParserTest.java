package test.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.parser.impl.DocumentParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DocumentParserTest {
    DocumentParser documentParser;

    @BeforeClass
    public void setUp() {
        documentParser = new DocumentParser();
    }

    @Test
    public void testParse() throws CompositeException {
        String document = """
                    It has survived - not only (five) centuries, but more-or-less the leap into distribution
                ob.toString(a?b:c) essentially unchanged Falcon9...
                    It is a long a!=b established fact "!." that a reader will be distracted by Lorem Ipsum
                content of a page when looking at its layout?
                    Bye бандерлоги!
                """;

        String actual = documentParser.parse(document).getComponents().toString();

        String expected = """
                [It has survived - not only (five) centuries, but more-or-less the leap into distribution \
                ob.toString(a?b:c) essentially unchanged Falcon9... , \
                It is a long a!=b established fact "!." that a reader will be distracted by Lorem Ipsum \
                content of a page when looking at its layout? , \
                Bye бандерлоги! ]\
                """;

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testNullDocumentParse() throws CompositeException {
        TextComponent documentComponent = documentParser.parse(null);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testEmptyDocumentParse() throws CompositeException {
        String document = "";
        TextComponent documentComponent = documentParser.parse(document);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testBlankDocumentParse() throws CompositeException {
        String document = "   \n  \t";
        TextComponent documentComponent = documentParser.parse(document);
    }
}


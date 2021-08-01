package test.fairycompany.handling.reader;

import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.reader.TextReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextReaderTest {
    TextReader reader;

    @BeforeMethod
    public void setUp() {
        reader = new TextReader();
    }

    @Test
    public void testReadFile() throws CompositeException {
        String path = "resources\\testdata\\testtext.txt";
        String actual = reader.readFile(path);
        String expected = """
                    It has survived - not only (five) centuries, but more-or-less the leap into distribution
                ob.toString(a?b:c) essentially unchanged Falcon9...
                    It is a long a!=b established fact "!." that a reader will be distracted by Lorem Ipsum
                content of a page when looking at its layout?
                    Bye бандерлоги!\
                """;
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CompositeException.class)
    public void testReadNullFileException() throws CompositeException {
        reader.readFile(null);
    }

    @Test(expectedExceptions = CompositeException.class)
    public void testReadEmptyFileNameException() throws CompositeException {
        String path = " ";
        reader.readFile(path);
    }

    @Test(expectedExceptions = CompositeException.class)
    public void testReadWrongFileException() throws CompositeException {
        String path = "resources\\test_data\\data.txt";
        reader.readFile(path);
    }
}
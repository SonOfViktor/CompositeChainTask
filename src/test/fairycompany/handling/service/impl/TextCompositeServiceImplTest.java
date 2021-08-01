package test.fairycompany.handling.service.impl;

import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.parser.impl.DocumentParser;
import com.fairycompany.handling.parser.impl.SentenceParser;
import com.fairycompany.handling.service.impl.TextCompositeServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextCompositeServiceImplTest {
    TextCompositeServiceImpl service;
    String document;
    String emptyDocument;

    @BeforeClass
    public void setUp() {
        service = new TextCompositeServiceImpl();
        document = """
                    Sentence 1. Другое sentence 2.
                    Sentence one. TheBiggeestWord in sentence two. Sentence three.
                    Sentence 1.
                 """;
        emptyDocument = "";
    }

    @Test
    public void testParagraphSort() throws CompositeException {
        DocumentParser documentParser = new DocumentParser();
        String sortedDocument = """
                    Sentence 1.
                    Sentence 1. Другое sentence 2.
                    Sentence one. TheBiggeestWord in sentence two. Sentence three.
                """;

        TextComponent actual = service.paragraphSort(document);
        TextComponent expected = documentParser.parse(sortedDocument);

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testParagraphSortEmptyText() throws CompositeException {
        TextComponent actual = service.paragraphSort(emptyDocument);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testParagraphSortNullText() throws CompositeException {
        TextComponent actual = service.paragraphSort(null);
    }

    @Test
    public void testFindSentenceLongestWord() throws CompositeException {
        SentenceParser sentenceParser = new SentenceParser();
        String longestWordSentence = "TheBiggeestWord in sentence two.";
        TextComponent actual = service.findSentenceLongestWord(document);
        TextComponent expected = sentenceParser.parse(longestWordSentence);

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testFindSentenceEmptyText() throws CompositeException {
        TextComponent actual = service.findSentenceLongestWord(emptyDocument);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testFindSentenceNullText() throws CompositeException {
        TextComponent actual = service.findSentenceLongestWord(null);
    }

    @Test
    public void testDeleteSentencesWordsLess() throws CompositeException {
        String expected = "Другое sentence 2. TheBiggeestWord in sentence two. ";
        String actual = service.deleteSentencesWordsLess(document, 2);

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testDeleteSentenceEmptyText() throws CompositeException {
        String actual = service.deleteSentencesWordsLess(emptyDocument, 2);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testDeleteSentenceNullText() throws CompositeException {
        String actual = service.deleteSentencesWordsLess(null, 2);
    }

    @Test
    public void testFindAmountSameWords() throws CompositeException {
        int expected = 8;
        int actual = service.findAmountSameWords(document);

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testFindAmountSameWordsEmptyText() throws CompositeException {
        int actual = service.findAmountSameWords(emptyDocument);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testFindAmountSameWordsNullText() throws CompositeException {
        int actual = service.findAmountSameWords(null);
    }

    @Test
    public void testCountVowel() throws CompositeException {
        int expected = 32;
        int actual = service.countVowel(document);

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testCountVowelEmptyText() throws CompositeException {
        int actual = service.countVowel(emptyDocument);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testCountVowelNullText() throws CompositeException {
        int actual = service.countVowel(null);
    }


    @Test
    public void testCountConsonant() throws CompositeException {
        int expected = 50;
        int actual = service.countConsonant(document);

        assertEquals(actual, expected);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testCountConsonantEmptyText() throws CompositeException {
        int actual = service.countConsonant(emptyDocument);
    }

    @Test (expectedExceptions = CompositeException.class)
    public void testCountConsonantNullText() throws CompositeException {
        int actual = service.countConsonant(null);
    }
}
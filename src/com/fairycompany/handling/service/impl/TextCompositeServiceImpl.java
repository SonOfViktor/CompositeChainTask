package com.fairycompany.handling.service.impl;

import com.fairycompany.handling.entity.CompositeType;
import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.entity.impl.Punctuation;
import com.fairycompany.handling.entity.impl.TextComposite;
import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.parser.impl.DocumentParser;
import com.fairycompany.handling.parser.impl.LexemeParser;
import com.fairycompany.handling.parser.impl.ParagraphParser;
import com.fairycompany.handling.parser.impl.WordParser;
import com.fairycompany.handling.service.TextCompositeService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TextCompositeServiceImpl implements TextCompositeService {
    private static Logger logger = LogManager.getLogger();
    private static final String VOWEL_REGEX = "(?i)(?u)[aeiouyаеиоуыэ-я]";
    private static final String CONSONANT_REGEX = "(?i)(?u)[\\p{Alpha}б-ь&&[^aeiouyеиоуы]]";

    public TextComponent paragraphSort(String document) throws CompositeException {
        DocumentParser documentParser = new DocumentParser();
        TextComponent documentComposite = documentParser.parse(document);
        TextComposite sortedDocument = new TextComposite(CompositeType.PARAGRAPH);
        List<TextComponent> paragraphs = documentComposite.getComponents();

        paragraphs.sort(Comparator.comparingInt(TextComponent::size));

        for (TextComponent paragraph : paragraphs) {
            sortedDocument.add(paragraph);
        }

        return sortedDocument;
    }

    public TextComponent findSentenceLongestWord (String document) throws CompositeException {
        ParagraphParser paragraphParser = new ParagraphParser();
        TextComponent paragraphComposite = paragraphParser.parse(document);
        List<TextComponent> sentences = paragraphComposite.getComponents();

        TextComponent longestWord = findLongestWord(document);
        String longestWordString = longestWord.toString();

        for (TextComponent sentence : sentences) {
            String sentenceString = sentence.toString();
            if (sentenceString.contains(longestWordString)) {
                return sentence;
            }
        }

        throw new CompositeException("It will be never shown");
    }

    public String deleteSentencesWordsLess(String document, int amount) throws CompositeException {
        ParagraphParser paragraphParser = new ParagraphParser();
        TextComponent paragraphComposite = paragraphParser.parse(document);
        List<TextComponent> sentences = paragraphComposite.getComponents();

        String newDocument = sentences.stream()
                                        .peek(o -> logger.log(Level.DEBUG, "Size of sentence {} is {}",
                                                            sentences.indexOf(o) + 1, countWords(o)))
                                        .filter(o -> countWords(o) > amount)
                                        .map(Object::toString)
                                        .collect(Collectors.joining());

        return newDocument;
    }

    public int findAmountSameWords(String document) throws CompositeException {
        LexemeParser lexemeParser = new LexemeParser();
        TextComponent lexemeComposite = lexemeParser.parse(document);
        List<TextComponent> words = deletePunctuation(lexemeComposite.getComponents());
        int totalCount = 0;

        while(words.size() != 1) {
            int wordCount = 1;
            String word = words.get(0).toString().toLowerCase();

            for (int j = 1; j < words.size(); j++) {
                String nextWord = words.get(j).toString().toLowerCase();

                if (word.equals(nextWord)) {
                    wordCount++;
                    words.remove(j);
                    j--;
                }
            }

            if (wordCount != 1) {
                totalCount += wordCount;
                logger.log(Level.DEBUG, "Word {} occurs {} times", word, wordCount);
            }

            words.remove(0);
        }

        return totalCount;
    }

    public int countVowel(String document) throws CompositeException {
        WordParser wordParser = new WordParser();
        TextComponent wordComposite = wordParser.parse(document);
        List<TextComponent> symbols = wordComposite.getComponents();

        long vowelAmount = symbols.stream()
                                    .filter(o -> o.toString().matches(VOWEL_REGEX))
                                    .peek(System.out::println)
                                    .count();

        logger.log(Level.INFO, "There are {} vowels in this text", vowelAmount);

        return (int) vowelAmount;
    }

    public int countConsonant(String document) throws CompositeException {
        WordParser wordParser = new WordParser();
        TextComponent wordComposite = wordParser.parse(document);
        List<TextComponent> symbols = wordComposite.getComponents();

        long consonantAmount = symbols.stream()
                                    .filter(o -> o.toString().matches(CONSONANT_REGEX))
                                    .peek(System.out::println)
                                    .count();

        logger.log(Level.INFO, "There are {} vowels in this text", consonantAmount);

        return (int) consonantAmount;
    }

    private TextComponent findLongestWord (String document) throws CompositeException {
        LexemeParser lexemeParser = new LexemeParser();
        TextComponent lexemeComposite = lexemeParser.parse(document);
        List<TextComponent> words = deletePunctuation(lexemeComposite.getComponents());

        TextComponent longestWord = words.stream()
                .max(Comparator.comparingInt(TextComponent::size)).orElseThrow(() ->
                        new CompositeException("Stream of words is empty"));

        logger.log(Level.DEBUG, "The longest word in text is {}", longestWord);

        return longestWord;
    }

    private int countWords(TextComponent sentence) {
        return (int) sentence.getComponents().stream().filter(o -> !isPunctuation(o)).count();
    }

    private boolean isPunctuation(TextComponent component) {
        return component.getClass().equals(Punctuation.class);
    }

    private List<TextComponent> deletePunctuation(List<TextComponent> components) {
       return components.stream().filter(o -> !isPunctuation(o)).collect(Collectors.toList());
    }
}

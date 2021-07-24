package com.fairycompany.handling.service;

import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.exception.CompositeException;

public interface TextCompositeService {

    TextComponent paragraphSort(String document) throws CompositeException;

    TextComponent findSentenceLongestWord (String document) throws CompositeException;

    String deleteSentencesWordsLess(String document, int amount) throws CompositeException;

    int findAmountSameWords(String document) throws CompositeException;

    int countVowel(String document) throws CompositeException;

    int countConsonant(String document) throws CompositeException;
}

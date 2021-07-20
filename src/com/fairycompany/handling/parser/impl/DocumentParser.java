package com.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.CompositeType;
import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.entity.impl.TextComposite;
import com.fairycompany.handling.parser.TextParser;

public class DocumentParser implements TextParser {
    private static final String DOCUMENT_DELIMITER_REGEX = "\\n\\s{4}";
    private TextParser paragraphParser = new ParagraphParser();

    @Override
    public TextComponent parse(String document) {
        TextComposite documentComposite = new TextComposite(CompositeType.DOCUMENT);
        String[] paragraphs = document.trim().split(DOCUMENT_DELIMITER_REGEX);

        for (String paragraph : paragraphs) {
            TextComponent paragraphComposite = paragraphParser.parse(paragraph);
            documentComposite.add(paragraphComposite);
        }

        return documentComposite;
    }
}

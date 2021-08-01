package com.fairycompany.handling.parser.impl;

import com.fairycompany.handling.entity.CompositeType;
import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.entity.impl.TextComposite;
import com.fairycompany.handling.exception.CompositeException;
import com.fairycompany.handling.parser.TextParser;

public class DocumentParser implements TextParser {
    private static final String DOCUMENT_DELIMITER_REGEX = "\\n\\s+";
    private TextParser paragraphParser = new ParagraphParser();

    @Override
    public TextComponent parse(String document) throws CompositeException {
        if (document == null || document.isBlank()) {
            throw new CompositeException("Given text is null or blank");
        }

        TextComposite documentComposite = new TextComposite(CompositeType.PARAGRAPH);
        String[] paragraphs = document.trim().split(DOCUMENT_DELIMITER_REGEX);

        for (String paragraph : paragraphs) {
            TextComponent paragraphComposite = paragraphParser.parse(paragraph);
            documentComposite.add(paragraphComposite);
        }

        return documentComposite;
    }
}

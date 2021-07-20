package com.fairycompany.handling.entity;

public enum CompositeType {
    DOCUMENT("\n\r\t"),
    PARAGRAPH(" "),
    SENTENCE(" "),
    LEXEME(""),
    WORD("");

    private final String delimiter;

    CompositeType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
